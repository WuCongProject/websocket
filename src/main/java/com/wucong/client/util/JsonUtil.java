package com.wucong.client.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;

/**
 * @Author: kerwin
 * @Date: 2020/10/27 15:55
 * @Description:
 */
@Slf4j
public class JsonUtil {

    /**
     * 解析请求 json -》 class
     * @param data
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T parseString2Object(String data, Class<T> cls){

        //判断是否为空
        if (StringUtils.isEmpty(data)){
            log.error(ErrorEnum.REQUEST_IS_EMPTY.getMsg());
            throw new ChatException(ErrorEnum.REQUEST_IS_EMPTY);
        }
        //解析json字符串
        T t = JSON.parseObject(data,cls);
        log.info("request data ：[{}] timeStamp:[ {} ]",t.toString(),System.currentTimeMillis());
        return t;
    }

    public static <T> T resolveJson(String jsonStr,Class<T> cls){

        //判断是否为空
        if (StringUtils.isEmpty(jsonStr)){
            log.error(ErrorEnum.HTTP_REQUEST_ERROR.getMsg());
            throw new ChatException(ErrorEnum.HTTP_REQUEST_ERROR);
        }

        try {
            JSONObject json = JSON.parseObject(jsonStr);
            int code = (int)json.get("code");
            if (code != 0){
                log.error("请求异常 jsonStr:{}",jsonStr);
                throw new ChatException(ErrorEnum.HTTP_REQUEST_ERROR.getCode(),(String) json.get("msg"));
            }
            log.info(json.get("data").toString());
            String data = json.get("data").toString();
            return parseString2Object(data,cls);
        }catch (Exception e){
            log.info("解析JSON失败 jsonStr:{}",jsonStr);
            throw new ChatException(ErrorEnum.JSON_RESOLVE_ERROR.getCode(),e.getMessage());
        }
    }

}
