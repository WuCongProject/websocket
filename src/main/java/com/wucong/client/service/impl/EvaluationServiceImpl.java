package com.wucong.client.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.wucong.client.convert.Convert;
import com.wucong.client.config.pojo.SystemConfig;
import com.wucong.client.dto.model.Evaluation;
import com.wucong.client.dto.service.EvaluationDtoService;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;
import com.wucong.client.pojo.*;
import com.wucong.client.service.EvaluationService;
import com.wucong.client.util.AesUtil;
import com.wucong.client.util.JsonUtil;
import com.wucong.client.util.RedisUtil;
import com.wucong.client.util.ShaUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 15:35
 * @Description: 平敬业务层
 */
@Slf4j
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EvaluationDtoService dtoService;

    @Autowired
    private SystemConfig config;

    @Override
    public EncryptResponse genEvaluation(ChatRequest<EncryptRequest> request) {
        //公共参数校验
        checkParam(request);
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try{
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(),param.get(0));
            //解密
            GenEvaluationRequest genEvaluationRequest = JsonUtil.parseString2Object(data,GenEvaluationRequest.class);
            //验证数据签名
            String sign = genEvaluationRequest.getSign();
            genEvaluationRequest.setSign(config.getSalt());
            ShaUtil.checkSign(genEvaluationRequest,sign);
            Evaluation evaluation = Convert.GenEvaluationRequest2Evaluation(genEvaluationRequest);
            evaluation = dtoService.genEvaluation(evaluation);
            GenEvaluationResponse response = Convert.Evaluation2GenEvaluationResponse(evaluation);
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
    public EncryptResponse delEvaluation(ChatRequest<EncryptRequest> request) {
        //公共参数校验
        checkParam(request);
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try{
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(),param.get(0));
            //解密
            DelEvaluationRequest delEvaluationRequest = JsonUtil.parseString2Object(data,DelEvaluationRequest.class);
            //验证数据签名
            String sign = delEvaluationRequest.getSign();
            delEvaluationRequest.setSign(config.getSalt());
            ShaUtil.checkSign(delEvaluationRequest,sign);
            dtoService.delEvaluation(delEvaluationRequest.getEvaluationId());
            DelEvaluationResponse response = new DelEvaluationResponse();
            response.setStatus("ok");
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
    public EncryptResponse queryEvaluation(ChatRequest<EncryptRequest> request) {
        //公共参数校验
        checkParam(request);
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try{
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(),param.get(0));
            //解密
            QueryEvaluationRequest genEvaluationRequest = JsonUtil.parseString2Object(data,QueryEvaluationRequest.class);
            //验证数据签名
            String sign = genEvaluationRequest.getSign();
            genEvaluationRequest.setSign(config.getSalt());
            ShaUtil.checkSign(genEvaluationRequest,sign);
            List<Evaluation> evaluations = dtoService.queryEvaluation(genEvaluationRequest);
            List<GenEvaluationResponse> evaluationResponses = new ArrayList<>();
            for (Evaluation evaluation : evaluations){
                GenEvaluationResponse evaluationResponse = Convert.Evaluation2GenEvaluationResponse(evaluation);
                evaluationResponses.add(evaluationResponse);
            }
            //查询数量
            Long count = dtoService.queryEvaluationCount(genEvaluationRequest);
            QueryEvaluationResponse response = new QueryEvaluationResponse();
            response.setArray(evaluationResponses);
            response.setCount(count);
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

    private <T> void checkParam(ChatRequest<T> request){

        Assert.notNull(request.getTimeStamp(),"时间戳不能为空");
        Assert.notNull(request.getUser(),"User不能为空");

    }

}
