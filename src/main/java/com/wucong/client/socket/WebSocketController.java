package com.wucong.client.socket;

/**
 * @author luhoo
 * @date 2021/3/19  13:11
 */

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wucong.client.convert.Convert;
import com.wucong.client.dto.model.ChatInfo;
import com.wucong.client.dto.service.ChatDtoService;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;
import com.wucong.client.pojo.MessageInfo;
import com.wucong.client.util.JsonUtil;
import com.wucong.client.util.RedisUtil;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ServerEndPoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端，
 * 注解的值将被用于监听用户连接的终端访问URL地址，客户端可以通过这个URL连接到websocket服务器端
 */
@ServerEndpoint("/chat/wc_chat/{token}")
@Component
@Slf4j
public class WebSocketController {
    @Autowired
    private ChatDtoService chatService;

    @Autowired
    private RedisUtil redisUtil;

    public static WebSocketController webSocketController;

    @PostConstruct
    public void init() {
        webSocketController = this;
        webSocketController.chatService = this.chatService;
        webSocketController.redisUtil = this.redisUtil;
    }

    /**
     * 静态变量，用来记录当前在线连接数。
     * 应该把它设计成线程安全的。
     */
    private static AtomicInteger onlineCount = new AtomicInteger();
    /**
     * concurrent包的线程安全Set，
     * 用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketController>
            webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，
     * 需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收userId
     */
    private String userId = "";

    private String businessId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {

        this.session = session;
        List<String> param = webSocketController.redisUtil.getParam(token);


        log.info("param：{}",param);
        //根据token获取用户id
        this.userId=param.get(1);
        //判断用户是否为客服用户
        if (param.get(3).equals("2")){
            this.businessId = param.get(4);
            webSocketController.redisUtil.add(this.businessId,this.userId);
        }
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            //加入set中
        } else {
            webSocketMap.put(userId, this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }
        log.info("redis:{}",webSocketController.redisUtil);
        log.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:" + userId + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //如果是客服 从商家客服列表中删除
            if (StringUtils.startsWith(this.userId, "S")) {
                webSocketController.redisUtil.remove(this.businessId, this.userId);
            }
            subOnlineCount();
        }
        log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户:" + userId + ",报文:" + message);

        //可以群发消息
        //消息保存到数据库、redis
        if (!StringUtils.isEmpty(message)) {
            MessageInfo messageInfo = JsonUtil.parseString2Object(message, MessageInfo.class);
            //追加发送人(防止串改)
            messageInfo.setSender(this.userId);
            //获取消息接受者
            ChatInfo chatInfo = Convert.messageInfo2ChatInfo(messageInfo);
            //判断消息是否被处理
            if (messageInfo.getType() == 3) {
                sendMsg2Customer(messageInfo, chatInfo);
            } else if (messageInfo.getType() == 1) {
                sendMsg2Bus(messageInfo);
            }else {
                throw new ChatException(ErrorEnum.SEND_MSG_ERROR.getCode(),"消息发送失败");
            }
            webSocketController.chatService.genChatInfo(chatInfo);
        }
    }

    /**
     * 给商家发送消息
     */
    private void sendMsg2Bus(MessageInfo messageInfo) {

        String sender = messageInfo.getSender();
        String businessId = messageInfo.getBusinessId();
        String staffId = webSocketController.redisUtil.get(businessId + sender);

        //判断用户 是否 已有客服处理
        if (!StringUtils.isEmpty(staffId)) {
            //判断客服是否在线
            if (!webSocketMap.containsKey(staffId)) {
                webSocketController.redisUtil.del(businessId + sender);
            } else {
                //2、标记该消息发送给 指定客服
                messageInfo.setType(2);
                String msg = JSON.toJSONString(messageInfo);
                sendMsg(msg, staffId, messageInfo.getBusinessId());
                //客服在线 发送消息后流程终止
                return;
            }
        }
        //1、标记该消息发送给所有客服
        messageInfo.setType(1);
        //客服未被处理 或客服不在线
        String msg = JSON.toJSONString(messageInfo);
        //获取商家员工列表
        Set<String> staffs = webSocketController.redisUtil.getSet(messageInfo.getBusinessId());
        //消息也发送到商家
        staffs.add(messageInfo.getBusinessId());
        log.info("staffs:{}",staffs);
        for (String staffReceiver : staffs) {
            sendMsg(msg, staffReceiver, messageInfo.getBusinessId());
        }
    }

    /**
     *
     * 给用户发送消息
     */
    private void sendMsg2Customer(MessageInfo messageInfo, ChatInfo chatInfo) {
        String businessId = messageInfo.getBusinessId();
        //设置消息处理人
        chatInfo.setChatHandler(messageInfo.getSender());
        //使用商家编号 覆盖发送人
        messageInfo.setSender(businessId);
        //商家id覆盖发送人Id
        chatInfo.setChatSenderId(businessId);
        //获取信息接受者
        String receiver = messageInfo.getReceiver();
        //标记消息发送给用户
        String msg = JSON.toJSONString(messageInfo);
        sendMsg(msg, receiver, messageInfo.getBusinessId());
    }

    /**
     * 发送消息
     *
     * @param msg
     * @param userId
     * @param businessId
     */
    private void sendMsg(String msg, String userId, String businessId) {
        try {
            log.info("user:{} webSocketMap:{} businessId msg:{}",userId,webSocketMap,businessId,msg);
            if (!StringUtils.isEmpty(userId) && webSocketMap.containsKey(userId)) {
                webSocketMap.get(userId).sendMessage(msg);
            } else {
                //如果客服已下线 将客服从客服列表删除
                if (StringUtils.startsWith(userId, "S")) {
                    redisUtil.remove(businessId, userId);
                }
                log.info("用户已下线:" + userId + "不在该服务器上");
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new ChatException(ErrorEnum.SEND_MSG_ERROR.getCode(), "消息发送失败");
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + ExceptionUtils.getStackTrace(error));
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (!StringUtils.isEmpty(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {

            log.error("用户" + userId + ",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    public static synchronized void addOnlineCount() {
        WebSocketController.onlineCount.incrementAndGet();
    }

    public static synchronized void subOnlineCount() {
        WebSocketController.onlineCount.decrementAndGet();
    }
}
