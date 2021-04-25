package com.wucong.client.controller;

import com.wucong.client.pojo.*;
import com.wucong.client.service.EvaluationService;
import com.wucong.client.util.RequestUtil;
import com.wucong.client.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 20:02
 * @Description: 评价接口
 */
@RestController
@RequestMapping("/chat/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService service;

    @RequestMapping(value = "/queryEvaluation")
    public ChatResponse<QueryUserResponse> queryEvaluation(@RequestBody ChatRequest<EncryptRequest> request){
        RequestUtil.requestJudge(request);
        EncryptResponse response = service.queryEvaluation(request);
        return ResultUtil.SUCCESS(response);
    }

    @RequestMapping(value = "/genEvaluation")
    public ChatResponse<QueryUserResponse> updateChatMsg(@RequestBody ChatRequest<EncryptRequest> request){

        RequestUtil.requestJudge(request);
        EncryptResponse response = service.genEvaluation(request);
        return ResultUtil.SUCCESS(response);
    }

    @RequestMapping(value = "/delChatMsg")
    public ChatResponse<QueryUserResponse> delEvaluation(@RequestBody ChatRequest<EncryptRequest> request){

        RequestUtil.requestJudge(request);
        EncryptResponse response = service.delEvaluation(request);
        return ResultUtil.SUCCESS(response);
    }

}
