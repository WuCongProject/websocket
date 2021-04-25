package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 13:52
 * @Description: 查询评价请求
 */
@Data
public class QueryEvaluationResponse {

    List<GenEvaluationResponse> array;

    private Long count;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
