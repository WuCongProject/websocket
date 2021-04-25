package com.wucong.client.enums;

import lombok.Getter;

/**
 * @Author: kerwin
 * @Date: 2020/11/29 16:20
 * @Description:
 */
@Getter
public enum  HttpEnum {

    HTTP_SUCCESS(200, "请求成功"),
    ;

    private int code;
    private String msg;

    HttpEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
