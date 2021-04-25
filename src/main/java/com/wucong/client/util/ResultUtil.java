package com.wucong.client.util;


import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.pojo.ChatResponse;

/**
 * @Author: kerwin
 * @Date: 2020/9/29 9:18
 * @Description:
 */
public class ResultUtil {

    public static <T> ChatResponse SUCCESS(T t) {

        ChatResponse<T> chatResponse = new ChatResponse<>();
        chatResponse.setCode(0);
        chatResponse.setMsg("SUCCESS");
        chatResponse.setData(t);
        return chatResponse;

    }

    public static ChatResponse ERROR(int code, String msg) {

        ChatResponse chatResponse = new ChatResponse<>();
        chatResponse.setCode(code);
        chatResponse.setMsg(msg);
        return chatResponse;

    }

    public static ChatResponse ERROR(ErrorEnum errorEnum) {

        ChatResponse chatResponse = new ChatResponse<>();
        chatResponse.setCode(errorEnum.getCode());
        chatResponse.setMsg(errorEnum.getMsg());
        return chatResponse;

    }


}
