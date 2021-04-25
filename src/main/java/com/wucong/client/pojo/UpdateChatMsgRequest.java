package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/1/17 18:22
 * @Description: 修改聊天信息状态请求
 */
@Data
public class UpdateChatMsgRequest {

    private List<String> chatMsgArray;

    private Integer status;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
