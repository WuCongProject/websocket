package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Author: kerwin
 * @Date: 2021/1/17 9:43
 * @Description: 加密请求
 */
@Data
public class EncryptRequest {

    private String data;

    private String token;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
