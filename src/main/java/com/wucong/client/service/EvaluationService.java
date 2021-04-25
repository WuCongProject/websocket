package com.wucong.client.service;


import com.wucong.client.pojo.ChatRequest;
import com.wucong.client.pojo.EncryptRequest;
import com.wucong.client.pojo.EncryptResponse;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 15:20
 * @Description: 评价业务层
 */
public interface EvaluationService {

    /**
     * 添加评价
     * @param request
     * @return
     */
    EncryptResponse genEvaluation(ChatRequest<EncryptRequest> request);

    /**
     * 删除评价
     * @param request
     * @return
     */
    EncryptResponse delEvaluation(ChatRequest<EncryptRequest> request);

    /**
     * 删除评价
     * @param request
     * @return
     */
    EncryptResponse queryEvaluation(ChatRequest<EncryptRequest> request);

}
