package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 13:52
 * @Description: 查询评价请求
 */
@Data
public class QueryEvaluationRequest {

    private String sendId;

    private String receiverId;

    private String commodityId;

    private Long startTime;

    private Long endTime;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

    /**
     * 签名
     */
    private String sign;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
