package com.wucong.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.wucong.client.convert.Convert;
import com.wucong.client.dto.model.ChatInfo;
import com.wucong.client.dto.service.ChatDtoService;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;
import com.wucong.client.pojo.*;
import com.wucong.client.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.wucong.client.util.AesUtil;
import com.wucong.client.util.JsonUtil;
import com.wucong.client.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/1/13 11:36
 * @Description: 聊天服务
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ChatDtoService dtoService;

    @Override
    public EncryptResponse checkStatus(ChatRequest<EncryptRequest> request) {
        return null;
    }

    @Override
    public EncryptResponse queryChatInfo(ChatRequest<EncryptRequest> request) {
        //公共参数校验
        checkParam(request);
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try{
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(),param.get(0));
            //解密
            QueryChatMsgRequest queryChatMsgRequest = JsonUtil.parseString2Object(data,QueryChatMsgRequest.class);

            if (param.get(3).equals(2)){
                redisUtil.saveToRedis(param.get(4)+queryChatMsgRequest.getReceiver(),
                        param.get(1),600);
            }
            List<ChatInfo> list = dtoService.queryChatInfo(queryChatMsgRequest);
            long count = dtoService.chatInfoCount(queryChatMsgRequest);

            List<QueryChatMsgResponse.ChatMsg> msgList = new ArrayList<>();
            List<String> idList = new ArrayList<>();
            //未读消息数量
            int statusCount = 0;
            for (ChatInfo chatInfo:list){
                if (chatInfo.getChatStatus().equals(0)){
                    statusCount++;
                }
                QueryChatMsgResponse.ChatMsg chatMsg = Convert.ChatInfo2ChatMsg(chatInfo);
                idList.add(chatInfo.getChatId());
                msgList.add(chatMsg);
            }
            dtoService.updateChatMsgStatus(idList,1);
            QueryChatMsgResponse response = new QueryChatMsgResponse();
            response.setChatMsgList(msgList);
            response.setStatusCount(statusCount);
            response.setMsgCount(count);
            //加密返回
            EncryptResponse encryptResponse = new EncryptResponse();
            String responseData = JSON.toJSONString(response);
            responseData = AesUtil.aesEncrypt(responseData,param.get(0));
            encryptResponse.setData(responseData);
            return encryptResponse;

        }catch(Exception e){
            log.error(ExceptionUtils.getStackTrace(e)+"timestamp:{}",System.currentTimeMillis());
            throw new ChatException(ErrorEnum.DECRYPT_ERROR.getCode(),ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public EncryptResponse queryChatUser(ChatRequest<EncryptRequest> request) {
        //公共参数校验
        checkParam(request);
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try{
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(),param.get(0));
            //解密
            QueryUserRequest userRequest = JsonUtil.parseString2Object(data,QueryUserRequest.class);

            List<ChatInfo> chatInfos = dtoService.queryChatUser(userRequest.getUserId());
            List<QueryChatMsgResponse.ChatMsg> msgList = new ArrayList<>();
            for (ChatInfo chatInfo:chatInfos){
                QueryChatMsgResponse.ChatMsg chatMsg = Convert.ChatInfo2ChatMsg(chatInfo);
                msgList.add(chatMsg);
            }
            QueryUserResponse response = new QueryUserResponse();
            response.setUsers(msgList);
            //加密返回
            EncryptResponse encryptResponse = new EncryptResponse();
            String responseData = JSON.toJSONString(response);
            responseData = AesUtil.aesEncrypt(responseData,param.get(0));
            encryptResponse.setData(responseData);
            return encryptResponse;

        }catch(Exception e){
            log.error(ExceptionUtils.getStackTrace(e)+"timestamp:{}",System.currentTimeMillis());
            throw new ChatException(ErrorEnum.DECRYPT_ERROR.getCode(),ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public EncryptResponse updateChatMsgStatus(ChatRequest<EncryptRequest> request) {
        //公共参数校验
        checkParam(request);
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try{
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(),param.get(0));
            //解密
            UpdateChatMsgRequest updateChatMsgRequest = JsonUtil.parseString2Object(data,UpdateChatMsgRequest.class);
            dtoService.updateChatMsgStatus(updateChatMsgRequest.getChatMsgArray(),updateChatMsgRequest.getStatus());
            //加密返回
            EncryptResponse encryptResponse = new EncryptResponse();
            String responseData = JSON.toJSONString("ok");
            responseData = AesUtil.aesEncrypt(responseData,param.get(0));
            encryptResponse.setData(responseData);
            return encryptResponse;

        }catch(Exception e){
            log.error(ExceptionUtils.getStackTrace(e)+"timestamp:{}",System.currentTimeMillis());
            throw new ChatException(ErrorEnum.DECRYPT_ERROR.getCode(),ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public EncryptResponse delChatInfo(ChatRequest<EncryptRequest> request) {
        //公共参数校验
        checkParam(request);
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try{
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(),param.get(0));
            //解密
            DelChatMsgRequset delChatMsgRequset = JsonUtil.parseString2Object(data,DelChatMsgRequset.class);
            delChatMsgRequset.setSenderId(param.get(1));
            dtoService.delChatInfo(delChatMsgRequset);
            //加密返回
            EncryptResponse encryptResponse = new EncryptResponse();
            String responseData = JSON.toJSONString("ok");
            responseData = AesUtil.aesEncrypt(responseData,param.get(0));
            encryptResponse.setData(responseData);
            return encryptResponse;

        }catch(Exception e){
            log.error(ExceptionUtils.getStackTrace(e)+"timestamp:{}",System.currentTimeMillis());
            throw new ChatException(ErrorEnum.DECRYPT_ERROR.getCode(),ExceptionUtils.getStackTrace(e));
        }
    }


    private <T> void checkParam(ChatRequest<T> request){

        Assert.notNull(request.getTimeStamp(),"时间戳不能为空");
        Assert.notNull(request.getUser(),"User不能为空");

    }


}
