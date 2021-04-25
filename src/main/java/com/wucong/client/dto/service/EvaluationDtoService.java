package com.wucong.client.dto.service;


import com.wucong.client.dto.model.Evaluation;
import com.wucong.client.pojo.QueryEvaluationRequest;

import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 13:07
 * @Description: 评价数据层
 */
public interface EvaluationDtoService {


    /**
     * 添加评价
     * @param evaluation
     * @return
     */
    Evaluation genEvaluation(Evaluation evaluation);

    /**
     * 查询评价
     * @param request
     * @return
     */
    List<Evaluation> queryEvaluation(QueryEvaluationRequest request);

    /**
     * 删除评价
     * @param id
     * @return
     */
    void delEvaluation(String id);

    /**
     * 查询评价条数
     * @param request
     * @return
     */
    Long queryEvaluationCount(QueryEvaluationRequest request);

}
