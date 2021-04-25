package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: kerwin
 * @Date: 2021/2/7 13:09
 * @Description: 添加评价请求
 */
@Data
public class GenEvaluationRequest {

    /**
     * 发送人Id
     */
    @NotNull(message = "发送人不能为空")
    private String senderId;

    /**
     * 发送人昵称
     */
    @NotNull(message = "发送人昵称不能为空")
    private String senderName;

    /**
     * 接收人Id
     */
    @NotNull(message = "接收人Id不能为空")
    private String receiverId;

    /**
     * 接收人昵称
     */
    @NotNull(message = "接收人昵称不能为空")
    private String receiverName;

    /**
     * 商品Id
     */
    @NotNull(message = "商品Id不能为空")
    private String commodityId;

    /**
     * 评价内容
     */
    private String message;

    /**
     * 评价级别
     */
    @NotNull(message = "评价级别不能为空")
    private BigDecimal level;

    /**
     * 评价图片
     */
    private List<String> image;

    /**
     * 评价视频
     */
    private String video;

    /**
     * 数据签名
     */
    private String sign;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
