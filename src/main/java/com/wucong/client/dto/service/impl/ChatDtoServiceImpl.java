package com.wucong.client.dto.service.impl;

import com.alibaba.fastjson.JSON;
import com.wucong.client.pojo.QueryChatMsgResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.wucong.client.config.pojo.SystemConfig;
import com.wucong.client.dto.mapper.ChatInfoMapper;
import com.wucong.client.dto.model.ChatInfo;
import com.wucong.client.dto.model.ChatInfoExample;
import com.wucong.client.dto.service.ChatDtoService;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;
import com.wucong.client.pojo.DelChatMsgRequset;
import com.wucong.client.pojo.QueryChatMsgRequest;
import com.wucong.client.pojo.UpdateChatMsgRequest;
import com.wucong.client.util.ArrayUtils;
import com.wucong.client.util.IdUtil;
import com.wucong.client.util.ShaUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: kerwin
 * @Date: 2021/1/13 11:33
 * @Description: 聊天信息服务实现类
 */
@Slf4j
@Service
public class ChatDtoServiceImpl implements ChatDtoService {

    @Autowired
    private ChatInfoMapper mapper;

    @Autowired
    private SystemConfig config;

    private final static String pattern = "YYYY-MM-DD HH:mm:ss";

    @Override
    public List<ChatInfo> checkStatus(String time, int status) {

        if (StringUtils.isEmpty(time)){
            log.error("时间 timeStamp:{}",System.currentTimeMillis());
            throw new ChatException(ErrorEnum.PARAM_IS_EMPTY);
        }
        Date date;
        try {
            DateFormat formatter = new SimpleDateFormat(pattern);
            date = formatter.parse(time);
        } catch (Exception e) {
            log.error("时间格式异常 time：{}， timeStamp:{}",time,System.currentTimeMillis());
            throw new ChatException(ErrorEnum.TIME_ERROR.getCode(), "时间格式异常");
        }

        ChatInfoExample example = new ChatInfoExample();
        ChatInfoExample.Criteria criteria = example.createCriteria();
        criteria.andChatHandlerEqualTo("");
        criteria.andChatCreateTimeLessThan(date);
        criteria.andChatStatusEqualTo(status);
        List<ChatInfo> list = mapper.selectByExample(example);
        if (ArrayUtils.isNotEmpty(list)){
            log.error("信息已被处理 timeStamp:{}",System.currentTimeMillis());
            return null;
        }
        return list;

    }

    @Override
    public List<ChatInfo> queryChatInfo(QueryChatMsgRequest queryChatMsg) {
        if (StringUtils.isEmpty(queryChatMsg.getReceiver()) ||
                StringUtils.isEmpty(queryChatMsg.getSender())){
            log.error("发送人 / 接收人 为空 timeStamp:{}",System.currentTimeMillis());
            throw new ChatException(ErrorEnum.SELECT_ERROR.getCode(),"发送人或接收人为空");
        }
        //封装查询条件
        ChatInfoExample example = genSelectExample(queryChatMsg);
        int page = queryChatMsg.getPage()*queryChatMsg.getOffset();
        RowBounds bounds = new RowBounds(page,queryChatMsg.getOffset());
        List<ChatInfo> list = mapper.selectByExampleWithRowbounds(example,bounds);
        log.info("sender:{},receiver:{},chatMsg:{}",queryChatMsg.getSender(),
                queryChatMsg.getReceiver(),list);
        return list;
    }

    @Override
    public List<ChatInfo> queryChatUser(String userId) {
        if (StringUtils.isEmpty(userId)){
            log.error("发送人 / 接收人 为空 timeStamp:{}",System.currentTimeMillis());
            throw new ChatException(ErrorEnum.SELECT_ERROR.getCode(),"发送人或接收人为空");
        }

        List<ChatInfo> chatInfos = mapper.selectReceiverMaxCreateTime(userId);
//        chatInfos.addAll(mapper.selectSenderMaxCreateTime(userId));
        log.info("user chat Msg : {}",chatInfos.toString());
        return chatInfos;
    }

    @Override
    public ChatInfo genChatInfo(ChatInfo chatInfo) {

        String id = IdUtil.getId();
        chatInfo.setChatId(id);
        chatInfo.setChatSign(config.getSalt());
        String sign = ShaUtil.sha1(JSON.toJSONString(chatInfo));
        chatInfo.setChatSign(sign);
        int n = mapper.insertSelective(chatInfo);
        if (n<0){
            log.error("聊天记录发送失败 chatInfo：{}",chatInfo.toString());
            throw new ChatException(ErrorEnum.INSERT_DATA_ERROR.getCode(),"聊天记录存储失败");
        }

        return chatInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateChatMsgStatus(List<String> idList, Integer status) {
       if(ArrayUtils.isNotEmpty(idList)){
           log.error("聊天信息为空");
           return null;
       }

       for (String chatId : idList){
           ChatInfo chatInfo = mapper.selectByPrimaryKey(chatId);
           if(StringUtils.isEmpty(chatInfo)){
               log.info("消息记录不存在 msg：{}",chatId);
               throw new ChatException(ErrorEnum.SELECT_ERROR.getCode(),"消息不存在");
           }
           String sign = chatInfo.getChatSign();
           chatInfo.setChatSign(config.getSalt());
           ShaUtil.checkSign(JSON.toJSONString(chatInfo),sign);
           chatInfo.setChatStatus(status);
           sign = ShaUtil.sha1(JSON.toJSONString(chatInfo));
           chatInfo.setChatSign(sign);
           int n = mapper.updateByPrimaryKey(chatInfo);
           if (n<0){
               log.error("聊天记录修改失败：chatInfo:{}",chatInfo);
               throw new ChatException(ErrorEnum.PARAM_IS_EMPTY.getCode(),"状态修改失败");
           }
       }
       return 0;
    }

    @Override
    public long chatInfoCount(QueryChatMsgRequest queryChatMsg) {
        ChatInfoExample example = genSelectExample(queryChatMsg);
        long count = mapper.countByExample(example);
        log.info("聊天记录总条数:{}",count);
        return count;
    }

    /**
     * 封装查询条件
     * @param queryChatMsg
     * @return
     */
    private ChatInfoExample genSelectExample(QueryChatMsgRequest queryChatMsg){

        ChatInfoExample example = new ChatInfoExample();
        ChatInfoExample.Criteria criteria = example.createCriteria();
        criteria.andChatReceiverIdEqualTo(queryChatMsg.getSender());
        criteria.andChatSenderIdEqualTo(queryChatMsg.getReceiver());

        ChatInfoExample.Criteria criteria1 = example.or();
        criteria1.andChatReceiverIdEqualTo(queryChatMsg.getReceiver());
        criteria1.andChatSenderIdEqualTo(queryChatMsg.getSender());

        //判断是否有日期限制
        if (!StringUtils.isEmpty(queryChatMsg.getCreateTime())){

            //获取起始日期
            Date startTime = DateUtils.parseDate(queryChatMsg.getCreateTime(),new String[]{pattern});
            judgeDatePattern(queryChatMsg.getCreateTime());
            //如果结束日期为空 将 结束日期设置为当前时间
            Date endTime = new Date();
            if (!StringUtils.isEmpty(queryChatMsg.getEndTime())){
                judgeDatePattern(queryChatMsg.getEndTime());
                endTime = DateUtils.parseDate(queryChatMsg.getEndTime(),new String[]{pattern});
            }
            criteria.andChatCreateTimeBetween(startTime,endTime);
            criteria1.andChatCreateTimeBetween(startTime,endTime);
        }

        example.setOrderByClause("wchat_create_time");
        return example;
    }

    private void judgeDatePattern(String time) {
        String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(time);
        boolean dateFlag = m.matches();
        if (!dateFlag) {
            log.error("时间格式异常 time：{}",time);
            throw new ChatException(ErrorEnum.PATTERN_ERROR.getCode(),"时间格式异常");
        }
    }

    @Override
    public Integer delChatInfo(DelChatMsgRequset delChatMsg) {

        if (StringUtils.isEmpty(delChatMsg)){
            log.error("聊天记录删除失败：chatId 为空");
            throw new ChatException(ErrorEnum.PARAM_IS_EMPTY.getCode(),"Id为空");
        }

        ChatInfo chatInfo = mapper.selectByPrimaryKey(delChatMsg.getChatId());
        chatInfo.setChatSenderId(delChatMsg.getSenderId());
        chatInfo.setChatReceiverId(delChatMsg.getReceiverId());
        chatInfo.setChatMessage(delChatMsg.getChatMsg());
        String sign = chatInfo.getChatSign();
        chatInfo.setChatSign(config.getSalt());
        ShaUtil.checkSign(chatInfo,sign);
        int n = mapper.deleteByPrimaryKey(delChatMsg.getChatId());
        if (n<0){
            log.error("聊天记录删除失败 delChatMsg:{}",delChatMsg);
            throw new ChatException(ErrorEnum.DELETE_ERROR.getCode(),"删除失败");
        }

        return n;
    }
}
