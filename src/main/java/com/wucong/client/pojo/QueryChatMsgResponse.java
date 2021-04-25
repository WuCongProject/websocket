package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/1/17 9:46
 * @Description: 查询聊条记录响应
 */
@Data
public class QueryChatMsgResponse {

    private List<ChatMsg> chatMsgList;

    private Long msgCount;

    private Integer statusCount;

    @Data
    public static class ChatMsg{

        private String sender;

        private String senderName;

        private String receiver;

        private String receiverName;

        private String msg;

        private String msgType;

        private Long sendTime;

        @Override
        public String toString(){
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }

}
