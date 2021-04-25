package com.wucong.client.controller;

import com.wucong.client.pojo.*;
import com.wucong.client.service.ChatService;
import com.wucong.client.util.RequestUtil;
import com.wucong.client.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kerwin
 * @Date: 2021/1/16 11:09
 * @Description: 用户相关接口
 */
@Slf4j
@RequestMapping("/chat/user")
@RestController
public class UserController {


    @Autowired
    private ChatService service;

    @RequestMapping(value = "/queryUsers")
    public ChatResponse<QueryUserResponse> queryUser(@RequestBody ChatRequest<EncryptRequest> request){
        log.info("request",request);
        RequestUtil.requestJudge(request);
        EncryptResponse response = service.queryChatUser(request);
        return ResultUtil.SUCCESS(response);
    }

    @RequestMapping(value = "/queryChatMsg")
    public ChatResponse<QueryUserResponse> queryChatMsg(@RequestBody ChatRequest<EncryptRequest> request){

        RequestUtil.requestJudge(request);
        EncryptResponse response = service.queryChatInfo(request);
        return ResultUtil.SUCCESS(response);
    }

    @RequestMapping(value = "/updateChatMsg")
    public ChatResponse<QueryUserResponse> updateChatMsg(@RequestBody ChatRequest<EncryptRequest> request){

        RequestUtil.requestJudge(request);
        EncryptResponse response = service.updateChatMsgStatus(request);
        return ResultUtil.SUCCESS(response);
    }

    @RequestMapping(value = "/delChatMsg")
    public ChatResponse<QueryUserResponse> delChatMsg(@RequestBody ChatRequest<EncryptRequest> request){

        RequestUtil.requestJudge(request);
        EncryptResponse response = service.delChatInfo(request);
        return ResultUtil.SUCCESS(response);
    }

}
