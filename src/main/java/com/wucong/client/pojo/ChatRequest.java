package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Author: kerwin
 * @Date: 2020/11/24 16:14
 * @Description:
 */
@Data
public class ChatRequest<T> {

    private String user;

    private long timeStamp;

    /**
     *     请求体
     */
    private T body;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
