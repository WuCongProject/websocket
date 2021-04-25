package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Author: kerwin
 * @Date: 2020/11/24 17:43
 * @Description:
 */

@Data
public class ChatResponse<T> {

    private int code;

    private String msg;

    private T data;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
