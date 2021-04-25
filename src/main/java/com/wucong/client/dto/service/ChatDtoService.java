package com.wucong.client.dto.service;


import com.wucong.client.dto.model.ChatInfo;
import com.wucong.client.pojo.DelChatMsgRequset;
import com.wucong.client.pojo.QueryChatMsgRequest;
import com.wucong.client.pojo.QueryChatMsgResponse;
import com.wucong.client.pojo.UpdateChatMsgRequest;

import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/1/13 11:33
 * @Description: 聊天信息服务
 */
public interface ChatDtoService {

    /**
     * 检查消息状态
     * @param status
     * @return
     */
    List<ChatInfo> checkStatus(String time, int status);

    /**
     * 查询消息
     * @param queryChatMsg
     * @return
     */
    List<ChatInfo> queryChatInfo(QueryChatMsgRequest queryChatMsg);

    /**
     * 查询消息
     * @param userId
     * @return
     */
    List<ChatInfo> queryChatUser(String userId);

    /**
     * 添加消息
     * @param chatInfo
     * @return
     */
    ChatInfo genChatInfo(ChatInfo chatInfo);

    /**
     * 修改聊天信息状态
     * @param chatMsgList
     * @return
     */
    Integer updateChatMsgStatus(List<String> chatMsgList, Integer status);

    /**
     * 查询消息总数
     * @param queryChatMsg
     * @return
     */
    long chatInfoCount(QueryChatMsgRequest queryChatMsg);

    /**
     * 删除消息
     * @param delChatMsg
     * @return
     */
    Integer delChatInfo(DelChatMsgRequset delChatMsg);

}