package com.wucong.client.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author luhoo
 * @date 2021/3/19  22:37
 */
@Data
public class StaffRequest {

    private String staffId;

    private String businessId;

    private String customerId;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
