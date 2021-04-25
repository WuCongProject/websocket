package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Author: kerwin
 * @Date: 2021/1/16 16:37
 * @Description: 消息报文
 */
@Data
public class MessageInfo {

    private String sender;

    private String senderName;

    private String receiver;

    private String receiverName;

    private String msg;

    // file sender video
    private String msgType;

    private String businessId;

    private Integer type;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
