package com.wucong.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;
import com.wucong.client.pojo.*;
import com.wucong.client.service.StaffService;
import com.wucong.client.util.AesUtil;
import com.wucong.client.util.JsonUtil;
import com.wucong.client.util.RedisUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luhoo
 * @date 2021/3/19  22:40
 */
@Slf4j
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public EncryptResponse register(ChatRequest<EncryptRequest> request) {
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try {
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(), param.get(0));
            //解密
            StaffRequest staffRequest = JsonUtil.parseString2Object(data, StaffRequest.class);
            redisUtil.add(staffRequest.getBusinessId(), staffRequest.getStaffId());
            StaffResponse staffResponse = new StaffResponse();
            staffResponse.setStatus("SUCCESS");
            String responseData = JSON.toJSONString(staffResponse);
            responseData = AesUtil.aesEncrypt(responseData, param.get(0));
            EncryptResponse encryptResponse = new EncryptResponse();
            encryptResponse.setData(responseData);
            return encryptResponse;

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e) + "timestamp:{}", System.currentTimeMillis());
            throw new ChatException(ErrorEnum.DECRYPT_ERROR.getCode(), ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public EncryptResponse exit(ChatRequest<EncryptRequest> request) {
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try {
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(), param.get(0));
            //解密
            StaffRequest staffRequest = JsonUtil.parseString2Object(data, StaffRequest.class);
            redisUtil.remove(staffRequest.getBusinessId(), staffRequest.getStaffId());
            StaffResponse staffResponse = new StaffResponse();
            staffResponse.setStatus("SUCCESS");
            String responseData = JSON.toJSONString(staffResponse);
            responseData = AesUtil.aesEncrypt(responseData, param.get(0));
            EncryptResponse encryptResponse = new EncryptResponse();
            encryptResponse.setData(responseData);
            return encryptResponse;

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e) + "timestamp:{}", System.currentTimeMillis());
            throw new ChatException(ErrorEnum.DECRYPT_ERROR.getCode(), ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public EncryptResponse chooseCustomer(ChatRequest<EncryptRequest> request) {
        EncryptRequest encryptRequest = request.getBody();
        String data;
        try {
            List<String> param = redisUtil.getParam(encryptRequest.getToken());
            //解密
            data = AesUtil.aesDecrypt(encryptRequest.getData(), param.get(0));
            //解密
            StaffRequest staffRequest = JsonUtil.parseString2Object(data, StaffRequest.class);
            String key = staffRequest.getBusinessId() + staffRequest.getCustomerId();
            String value = redisUtil.get(key);
            StaffResponse staffResponse = new StaffResponse();
            if (StringUtils.isEmpty(value)) {
                redisUtil.saveToRedis(key, staffRequest.getStaffId(), 60);
                staffResponse.setStatus("SUCCESS");
            }else{
                staffResponse.setStatus("FAIL");
            }
            String responseData = JSON.toJSONString(staffResponse);
            responseData = AesUtil.aesEncrypt(responseData, param.get(0));
            EncryptResponse encryptResponse = new EncryptResponse();
            encryptResponse.setData(responseData);
            return encryptResponse;

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e) + "timestamp:{}", System.currentTimeMillis());
            throw new ChatException(ErrorEnum.DECRYPT_ERROR.getCode(), ExceptionUtils.getStackTrace(e));
        }
    }
}
