package com.wucong.client.controller;

import com.wucong.client.pojo.*;
import com.wucong.client.service.StaffService;
import com.wucong.client.util.RequestUtil;
import com.wucong.client.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luhoo
 * @date 2021/3/19  22:35
 */
@RequestMapping("/chat/staff")
@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/chooseCustomer")
    public ChatResponse<QueryUserResponse> chooseCustomer(@RequestBody ChatRequest<EncryptRequest> request){
        RequestUtil.requestJudge(request);
        EncryptResponse response = staffService.chooseCustomer(request);
        return ResultUtil.SUCCESS(response);
    }

    @RequestMapping(value = "/register")
    public ChatResponse<QueryUserResponse> register(@RequestBody ChatRequest<EncryptRequest> request){
        RequestUtil.requestJudge(request);
        EncryptResponse response = staffService.register(request);
        return ResultUtil.SUCCESS(response);
    }

    @RequestMapping(value = "/exit")
    public ChatResponse<QueryUserResponse> exit(@RequestBody ChatRequest<EncryptRequest> request){
        RequestUtil.requestJudge(request);
        EncryptResponse response = staffService.exit(request);
        return ResultUtil.SUCCESS(response);
    }

}
