package com.wucong.client.util;

import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;
import com.wucong.client.pojo.ChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @Author: kerwin
 * @Date: 2020/12/1 17:10
 * @Description: 请求工具
 */
@Slf4j
public class RequestUtil {

    /**
     *     统一判断请求
     * @param request
     * @param <T>
     */
    public static  <T> void requestJudge(ChatRequest<T> request){

        if (StringUtils.isEmpty(request)){
            log.error("request is empty timeStamp:{}",System.currentTimeMillis());
            throw new ChatException(ErrorEnum.REQUEST_IS_EMPTY);
        }
    }

}
