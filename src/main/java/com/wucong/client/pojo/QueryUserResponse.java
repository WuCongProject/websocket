package com.wucong.client.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/1/16 11:19
 * @Description: 查询用户响应
 */
@Data
public class QueryUserResponse {

    List<QueryChatMsgResponse.ChatMsg> users;

}
