package com.wucong.client.config.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.wucong.client.config.pojo.RedisConfig;

/**
 * @Author: kerwin
 * @Date: 2020/10/24 17:34
 * @Description:
 */
@EnableCaching
@Configuration
@Slf4j
public class RedisConfiguration extends CachingConfigurerSupport {

    @Autowired
    private RedisConfig config;

    /**
     * 功能描述: 具备连接池的lettuce连接工厂
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        log.info(config.toString());
        //连接池配置
        RedisConfig.Pool pool = config.getLettuce().getPool();
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(pool.getMaxIdle());
        poolConfig.setMinIdle(pool.getMinIdle());
        poolConfig.setMaxWaitMillis(pool.getMaxWait().toMillis());
        poolConfig.setMaxTotal(pool.getMaxActive());
        // 单机配置初始化
        LettucePoolingConfiguration configuration =
                new LettucePoolingConfiguration(
                        poolConfig, config.getHost(), config.getPort(), config.getTimeout(),config.getPassword(),config.getDatabase());
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration.getStandaloneConfiguration(),configuration);
        //一定要执行这个方法，才能使得factory生效
        return factory;
    }

    /**
     * 功能描述: redis模型
     */
    @Bean
    public <K, V> RedisTemplate<K, V> redisTemplate() {
        RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 功能描述: string类型的template
     * 键和值都是String,主要功能就是方便String类型的存储
     *
     * @param
     * @return:org.springframework.data.redis.core.StringRedisTemplate
     * @since: v1.0
     * @Author:wangcanfeng
     * @Date: 2019/4/1 16:45
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        return new StringRedisTemplate(lettuceConnectionFactory());
    }

    /**
     * 功能描述: 缓存管理器
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        //springboot2.X以上版本引入的缓存管理器和以前版本的缓存管理器初始化方式不一样，
        // 新版本的缓存管理器通过连接工程进行初始化
        return RedisCacheManager.create(lettuceConnectionFactory());
    }

    /**
     * 功能描述: 缓存键的生成器
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            // 目标类的类名
            sb.append(target.getClass().getName());
            // 目标方法名
            sb.append('#').append(method.getName());
            if (ObjectUtils.isEmpty(params)) {
                sb.append("()");
            } else {
                sb.append("(");
                for (Object param : params) {
                    if (param != null) {
                        sb.append(param).append(",");
                    } else {
                        // 参数为空的时候拼接上null
                        sb.append("null").append(",");
                    }
                }
                //移除最后一个逗号
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
            }
            return sb.toString();
        };
    }

    /**
     * 功能描述: 缓存解析器，可以通过定时的方式，对缓存中的内容进行处理
     */
    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(cacheManager());
    }

    /**
     * 功能描述: 缓存异常处理器
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }
}