package com.wucong.client.service;


import com.wucong.client.pojo.ChatRequest;
import com.wucong.client.pojo.EncryptRequest;
import com.wucong.client.pojo.EncryptResponse;

/**
 * @Author: kerwin
 * @Date: 2020/11/17 14:24
 * @Description:
 */
public interface ChatService {


    /**
     * 检查消息状态
     * @param request
     * @return
     */
    EncryptResponse checkStatus(ChatRequest<EncryptRequest> request);

    /**
     * 查询消息
     * @param request
     * @return
     */
    EncryptResponse queryChatInfo(ChatRequest<EncryptRequest> request);

    /**
     * 查询消息
     * @param request
     * @return
     */
    EncryptResponse queryChatUser(ChatRequest<EncryptRequest> request);

    /**
     * 修改聊天信息状态
     * @param request
     * @return
     */
    EncryptResponse updateChatMsgStatus(ChatRequest<EncryptRequest> request);

    /**
     * 删除消息
     * @param request
     * @return
     */
    EncryptResponse delChatInfo(ChatRequest<EncryptRequest> request);

}
