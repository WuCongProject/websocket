package com.wucong.client.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;
import com.wucong.client.pojo.ChatResponse;
import com.wucong.client.util.ResultUtil;

/**
 * @Author: kerwin
 * @Date: 2020/9/29 9:34
 * @Description:
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ChatResponse handle(Exception e) {
        if (e instanceof ChatException) {
            ChatException commException = (ChatException) e;
            return ResultUtil.ERROR(commException.getCode(), commException.getMessage());
        }
        return ResultUtil.ERROR(ErrorEnum.UNKNOW_ERROR.getCode(), e.getMessage());
    }
}
