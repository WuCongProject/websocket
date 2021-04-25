package com.wucong.client.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import com.wucong.client.config.util.RedisConfiguration;
import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: kerwin
 * @Date: 2020/11/15 11:59
 * @Description:
 */
@Slf4j
@Configuration
public class RedisUtil {

    @Autowired
    private RedisConfiguration redisConfiguration;

    /**
     * 从redis中取出redVal
     * @param token
     * @return
     */
    public List<String> getParam(String token){
        RedisTemplate<Object, Object> template = redisConfiguration.redisTemplate();
        String redVal = (String)template.opsForValue().get(token);
        if (StringUtils.isEmpty(redVal)){
            log.error("token异常 token：{}，timeStamp：{}",token,System.currentTimeMillis());

            throw new ChatException(ErrorEnum.TOKEN_ERROR.getCode(),"登录超时");
        }
        redVal = new String(Base64.decodeBase64(redVal.getBytes()));
        log.info("redVal: {}",redVal);
        String[] param = redVal.split("\\|");
        if (param.length<3){
            log.error("redVal 长度小与3");
            throw new ChatException(ErrorEnum.TOKEN_ERROR);
        }
        return Arrays.asList(param);
    }

    /**
     * 获取客服列表
     * @param businessId
     * @return
     */
    public Set<String> getSet(String businessId){
        RedisTemplate<Object, Object> template = redisConfiguration.redisTemplate();
        Set<Object> members = template.opsForSet().members(businessId);
        log.info("size:{}",members.size());
        Set<String> strSet = new HashSet<>();
        for (Object o : members){
            strSet.add((String) o);
        }
        return strSet;
    }

    /**
     * 添加一个客服
     * @param businessId
     * @param userId
     * @return
     */
    public void add(String businessId,String userId){
        RedisTemplate<Object, Object> template = redisConfiguration.redisTemplate();
        Long add = template.opsForSet().add(businessId, userId);

        log.info("add:{}",add);
    }

    /**
     * 判断用户是否被客服接收
     * @param sendId
     * @return
     */
    public String get(String sendId){
        RedisTemplate<Object, Object> template = redisConfiguration.redisTemplate();
        try {
            String redVal = (String)template.opsForValue().get(sendId);
            log.info("redVal:{}",redVal);
            return redVal;
        }catch (Exception e){
            log.error("getParamError:{}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 删除指定客服
     * @param businessId
     * @param userId
     * @return
     */
    public void remove(String businessId, String userId){
        RedisTemplate<Object, Object> template = redisConfiguration.redisTemplate();
        Long remove = template.opsForSet().remove(businessId, userId);
        log.info("remove:{}",remove);
    }


    /**
     * 删除键值
     * @param key
     * @return
     */
    public void del(String key){
        RedisTemplate<Object, Object> template = redisConfiguration.redisTemplate();
        Boolean delete = template.delete(key);
        if (!delete){
            log.error("删除键值是吧 key：{}",key);
            throw new ChatException(ErrorEnum.DELETE_ERROR.getCode(),"键值删除失败");
        }
    }

    /**
     * 将数据存入redis
     * @param key
     * @param value
     * @param time
     */
    public void saveToRedis(String key,String value,int time){
        value = Base64.encodeBase64String(value.getBytes());
        RedisTemplate<Object, Object> template = redisConfiguration.redisTemplate();
        if(time>0) {
            template.opsForValue().set(key, value, time, TimeUnit.MINUTES);
        }else{
            template.opsForValue().set(key,value);
        }
    }

}
