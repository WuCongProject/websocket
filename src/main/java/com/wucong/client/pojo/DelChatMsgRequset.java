package com.wucong.client.pojo;

import lombok.Data;

/**
 * @Author: kerwin
 * @Date: 2021/1/18 12:36
 * @Description: 删除消息请求
 */
@Data
public class DelChatMsgRequset {

    private String chatId;

    private String chatMsg;

    private String receiverId;

    private String senderId;
}
