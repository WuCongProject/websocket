package com.wucong.client.config.pojo;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Author: kerwin
 * @Date: 2020/10/21 17:41
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private int database = 0;

    private String host;

    private int port;

    private String password;

    private Duration timeout;

    private Lettuce lettuce;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Data
    @Component
    public static class Lettuce{

        private Pool pool;

        @Override
        public String toString(){
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }

    @Data
    public static class Pool{

        private int maxActive;

        private Duration maxWait;

        private int maxIdle;

        private int minIdle;
        @Override
        public String toString(){
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }

}
