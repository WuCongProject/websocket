package com.wucong.client.enums;

import lombok.Getter;

/**
 * @Author: kerwin
 * @Date: 2020/9/29 9:27
 * @Description:
 */

@Getter
public enum ErrorEnum {

    ERROR_RESULT(50000 | 5000, "系统错误"),
    SELECT_ERROR(50000 | 5001, "查询失败"),
    UNKNOW_ERROR(50000 | 50002, "未知错误"),
    REQUEST_IS_EMPTY(50000 | 5003, "请求为空"),
    LOGIN_FAIL(50000 | 5004, "注册失败"),
    INSERT_DATA_EMPTY(50000 | 5005, "添加数据为空"),
    INSERT_DATA_ERROR(50000 | 5006, "数据添加失败"),
    CALL_HTTP_FAIL(50000 | 5007, "HTTP 请求异常"),
    CALL_URL_ERROR(50000 | 5008, "HTTP URL格式异常"),
    CALL_IO_ERROR(50000 | 5009, "HTTP IO异常"),
    ENCRYPT_ERROR(50000 | 5010, "加密失败"),
    TOKEN_ERROR(20 | 20, "token异常"),
    FILE_NOT_FOUND(5000 | 5012,"文件未找到"),
    CLASS_NOT_FOUND(5000 | 5013,"类丢失"),
    IO_ERROR(5000 | 5014,"文件未找到"),
    OUT_PUT_STREAM_ERROR(80000 | 5015, "解码失败"),
    BALANCE_STATEMENT_ERROR(50000 | 5016, "余额交易异常"),
    ID_CREATE_ERROR(50000 | 5017, "Id创建失败"),
    SIGN_CHECK_ERROR(50000 | 5028, "sign验证失败"),
    PARAM_IS_EMPTY(50000 | 5019, "参数为空"),
    JSON_RESOLVE_ERROR(50000 | 5020, "JSON解析异常"),
    TIME_ERROR(50000 | 5021, "时间异常"),
    HTTP_REQUEST_ERROR(50000 | 5022, "HTTP请求异常"),
    SEND_MSG_ERROR(50000 | 5023, "发送消息异常"),
    PATTERN_ERROR(50000 | 5024, "格式错误"),
    DELETE_ERROR(50000 | 5025, "删除失败"),
    DECRYPT_ERROR(50000 | 5026, "解密失败"),
    EVALUATION_ERROR(50000 | 5026, "评价异常"),
    ;



    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
