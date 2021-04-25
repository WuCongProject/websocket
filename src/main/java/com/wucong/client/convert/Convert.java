package com.wucong.client.convert;

import org.apache.http.client.utils.DateUtils;
import org.springframework.util.StringUtils;
import com.wucong.client.dto.model.ChatInfo;
import com.wucong.client.dto.model.Evaluation;
import com.wucong.client.pojo.GenEvaluationRequest;
import com.wucong.client.pojo.GenEvaluationResponse;
import com.wucong.client.pojo.MessageInfo;
import com.wucong.client.pojo.QueryChatMsgResponse;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/1/16 16:21
 * @Description: 对象转换
 */
public class Convert {

    public static ChatInfo messageInfo2ChatInfo(MessageInfo messageInfo){

        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setChatSenderId(messageInfo.getSender());
        chatInfo.setChatSenderName(messageInfo.getSenderName());
        chatInfo.setChatMessage(messageInfo.getMsg());
        chatInfo.setChatReceiverId(messageInfo.getReceiver());
        chatInfo.setChatReceiverName(messageInfo.getReceiverName());
        chatInfo.setChatMessageType(messageInfo.getMsgType());
        //0 未读
        chatInfo.setChatStatus(0);
        chatInfo.setChatCreateTime(new Date());
        return chatInfo;
    }

    public static QueryChatMsgResponse.ChatMsg ChatInfo2ChatMsg(ChatInfo chatInfo){
        QueryChatMsgResponse.ChatMsg chatMsg = new QueryChatMsgResponse.ChatMsg();
        chatMsg.setMsg(chatInfo.getChatMessage());
        chatMsg.setReceiver(chatInfo.getChatReceiverId());
        chatMsg.setSender(chatInfo.getChatSenderId());
        chatMsg.setReceiverName(chatInfo.getChatReceiverName());
        chatMsg.setSenderName(chatInfo.getChatSenderName());
        chatMsg.setSendTime(chatInfo.getChatCreateTime().getTime());
        chatMsg.setMsgType(chatInfo.getChatMessageType());
        return chatMsg;
    }

    public static Evaluation GenEvaluationRequest2Evaluation(GenEvaluationRequest request){
        Evaluation evaluation = new Evaluation();
        if (!StringUtils.isEmpty(request.getImage())){
            evaluation.setEvaluationImage(String.join(",",request.getImage()));
        }
        evaluation.setEvaluationLevel(request.getLevel());
        evaluation.setEvaluationMessage(request.getMessage());
        evaluation.setEvaluationCommodityId(request.getCommodityId());
        evaluation.setEvaluationReceiverName(request.getReceiverName());
        evaluation.setEvaluationReceiverId(request.getReceiverId());
        evaluation.setEvaluationVideo(request.getVideo());
        evaluation.setEvaluationSenderId(request.getSenderId());
        evaluation.setEvaluationSenderName(request.getSenderName());
        return evaluation;
    }

    public static GenEvaluationResponse Evaluation2GenEvaluationResponse(Evaluation evaluation){
        GenEvaluationResponse evaluationResponse = new GenEvaluationResponse();
        if (!StringUtils.isEmpty(evaluation.getEvaluationImage())){
            String[] split = evaluation.getEvaluationImage().split(",");
            List<String> images = Arrays.asList(split);
            evaluationResponse.setImage(images);
        }
        evaluationResponse.setLevel(evaluation.getEvaluationLevel());
        evaluationResponse.setMessage(evaluation.getEvaluationMessage());
        evaluationResponse.setCommodityId(evaluation.getEvaluationCommodityId());
        evaluationResponse.setReceiverName(evaluation.getEvaluationReceiverName());
        evaluationResponse.setReceiverId(evaluation.getEvaluationReceiverId());
        evaluationResponse.setVideo(evaluation.getEvaluationVideo());
        evaluationResponse.setSenderId(evaluation.getEvaluationSenderId());
        evaluationResponse.setSenderName(evaluation.getEvaluationSenderName());
        evaluationResponse.setId(evaluation.getEvaluationId());
        return evaluationResponse;
    }



}
