package com.wucong.client.config.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: kerwin
 * @Date: 2020/10/27 14:31
 * @Description:
 */
@Data
@Component
@PropertySource(value = "file:/root/data/wucong/websocket/config.properties",ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "system")
public class SystemConfig {

    /**
     *  固定秘钥
     */
    private String aesKey;

    /**
     * 文件路径
     */
    private String imgPath;

    /**
     * 固盐
     */
    private String salt;

    /**
     * 签名偏移量
     */
    private String signOffset;

    /**
     * 服务端私钥路径
     */
    private String rsaPath;

    /**
     * token 在redis中存活时间
     */
    private int tokenKeepAlive;

    /**
     * 收货地址数量
     */
    private int shipAddressCount;

}
