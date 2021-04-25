package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Author: kerwin
 * @Date: 2021/1/17 9:44
 * @Description: 查询聊天记录
 */
@Data
public class QueryChatMsgRequest {

    private String sender;

    private String receiver;

    private String createTime;

    private String endTime;

    private Integer Offset;

    private Integer page;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
