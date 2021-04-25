package com.wucong.client.pojo;

import lombok.Data;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 18:08
 * @Description: 删除评价请求
 */
@Data
public class DelEvaluationRequest {

    private String evaluationId;

    private String sign;

}
