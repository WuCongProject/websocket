package com.wucong.client.dto.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.wucong.client.config.pojo.SystemConfig;
import com.wucong.client.dto.mapper.EvaluationMapper;
import com.wucong.client.dto.model.Evaluation;
import com.wucong.client.dto.model.EvaluationExample;
import com.wucong.client.dto.service.EvaluationDtoService;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;
import com.wucong.client.pojo.QueryEvaluationRequest;
import com.wucong.client.util.IdUtil;
import com.wucong.client.util.ShaUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 13:08
 * @Description: 评价数据层
 */
@Slf4j
@Service
public class EvaluationDtoServiceImpl implements EvaluationDtoService {

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private EvaluationMapper mapper;

    @Override
    public Evaluation genEvaluation(Evaluation evaluation) {

        String id = IdUtil.getId("PJ");
        evaluation.setEvaluationId(id);
        evaluation.setEvaluationNum(0);
        //最大值为5.0
        BigDecimal level = evaluation.getEvaluationLevel();
        //防止 级别为负数
        if (level.compareTo(new BigDecimal("5.0"))>0||level.compareTo(new BigDecimal(0))<0){
            log.error("评价添加失败 评价级别异常 evaluation:{}",evaluation);
            throw new ChatException(ErrorEnum.EVALUATION_ERROR.getCode(),"评价插入失败");
        }
        String sign = genSign(evaluation);
        evaluation.setEvaluationSign(sign);
        int n = mapper.insertSelective(evaluation);

        if (n<0){
            log.error("评价添加失败 evaluation:{}",evaluation);
            throw new ChatException(ErrorEnum.EVALUATION_ERROR.getCode(),"评价插入失败");
        }

        return evaluation;

    }

    @Override
    public List<Evaluation> queryEvaluation(QueryEvaluationRequest request) {
        EvaluationExample example = genExample(request);
        example.setOrderByClause("wevaluation_create_time");
        RowBounds rowBounds = new RowBounds(request.getPageNo()*request.getPageSize(),request.getPageSize());
        List<Evaluation> evaluations = mapper.selectByExampleWithRowbounds(example, rowBounds);
        return  evaluations;
    }

    @Override
    public void delEvaluation(String id) {
        Evaluation evaluation = mapper.selectByPrimaryKey(id);
        String sign = genSign(evaluation);
        if (!evaluation.getEvaluationSign().equals(sign)){
            log.error("签名验证失败 evaluation:{}",evaluation);
            throw new ChatException(ErrorEnum.EVALUATION_ERROR.getCode(),"评价删除失败");
        }
        int n = mapper.deleteByPrimaryKey(id);
        if (n<0){
            log.error("评价删除失败 evaluation:{}",id);
            throw new ChatException(ErrorEnum.EVALUATION_ERROR.getCode(),"评价删除失败");
        }
    }

    @Override
    public Long queryEvaluationCount(QueryEvaluationRequest request) {
        EvaluationExample example = genExample(request);
        long count = mapper.countByExample(example);
        return  count;
    }

    private EvaluationExample genExample(QueryEvaluationRequest request){
        EvaluationExample example = new EvaluationExample();
        EvaluationExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(request.getCommodityId())){
            criteria.andEvaluationCommodityIdEqualTo(request.getCommodityId());
        }
        if (!StringUtils.isEmpty(request.getSendId())){
            criteria.andEvaluationSenderIdEqualTo(request.getSendId());
        }
        if (!StringUtils.isEmpty(request.getReceiverId())){
            criteria.andEvaluationReceiverIdEqualTo(request.getReceiverId());
        }
        if (!StringUtils.isEmpty(request.getStartTime())){
            Date startTime = new Date(request.getStartTime());
            Date endTime = new Date();
            if (!StringUtils.isEmpty(request.getEndTime())){
                endTime = new Date(request.getEndTime());
            }
            criteria.andEvaluationCreateTimeBetween(startTime,endTime);
        }
        return example;
    }


    /**
     * 生成签名
     * @param evaluation
     * @return
     */
    private String genSign(Evaluation evaluation){

        Map<String,String> map = new LinkedHashMap<>();
        map.put("evaluationId",evaluation.getEvaluationId());
        map.put("evaluationSenderId",evaluation.getEvaluationSenderId());
        map.put("evaluationSenderName",evaluation.getEvaluationSenderName());
        map.put("evaluationReceiverId",evaluation.getEvaluationReceiverId());
        map.put("evaluationReceiverName",evaluation.getEvaluationReceiverName());
        map.put("evaluationCommodityId",evaluation.getEvaluationCommodityId());
        map.put("evaluationMessage",evaluation.getEvaluationMessage());
        map.put("salt",systemConfig.getSalt());
        String sign = ShaUtil.sha1(JSON.toJSONString(map));
        log.info("evaluation ：{} sign：{}",evaluation,sign);
        return sign;

    }

}
