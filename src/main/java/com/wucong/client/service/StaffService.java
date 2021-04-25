package com.wucong.client.service;

import com.wucong.client.pojo.ChatRequest;
import com.wucong.client.pojo.EncryptRequest;
import com.wucong.client.pojo.EncryptResponse;

/**
 * @author luhoo
 * @date 2021/3/19  22:36
 */
public interface StaffService {

    /**
     * 注册
     * @param request
     * @return
     */
    EncryptResponse register(ChatRequest<EncryptRequest> request);

    /**
     * 注册
     * @param request
     * @return
     */
    EncryptResponse exit(ChatRequest<EncryptRequest> request);

    /**
     * 注册
     * @param request
     * @return
     */
    EncryptResponse chooseCustomer(ChatRequest<EncryptRequest> request);
}
