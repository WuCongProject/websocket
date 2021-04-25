package com.wucong.client.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import com.wucong.client.enums.ErrorEnum;

/**
 * @Author: kerwin
 * @Date: 2020/9/29 9:26
 * @Description:
 */

@Data
@Slf4j
public class ChatException extends RuntimeException {

    private Integer code;

    public ChatException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ChatException(Integer code, String message, Exception e) {
        super(message);
        log.error(e.getMessage());
        this.code = code;
    }


    public ChatException(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.code = errorEnum.getCode();
    }

    public ChatException(ErrorEnum errorEnum, Exception e) {
        super(errorEnum.getMsg());
        log.error(ExceptionUtils.getStackTrace(e));
        this.code = errorEnum.getCode();
    }

    public ChatException(Exception e, int errorCode){
        super(e.getMessage());
        log.error(ExceptionUtils.getStackTrace(e));
        this.code = errorCode;
    }

}
