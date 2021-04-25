package com.wucong.client.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author: kerwin
 * @Date: 2020/9/30 11:11
 * @Description:
 */
@Slf4j
public class ShaUtil {

    public static String genHashStr(Map<String,Object> map){
        StringBuilder stringBuilder = new StringBuilder();
        //拼接字符串 用来进行sha运算
        String s= JSON.toJSONString(map);
        String str="["+s+"]";

        String token = sha1(str);
        log.info("str:{} token：{}",str,token);
        return token;
    }

    public static String sha1(String decrypt) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            String hexString = AesUtil.bytes2Hex(messageDigest);
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成数据签名
     * @param map
     * @return
     */
    public static String genHashSign(Map<String,Object> map){

        //拼接字符串 用来进行sha运算
        String s=JSON.toJSONString(map);
        String str="["+s+"]";

        String token = sha1(str);
        log.info("str:{} sign：{}",str,token);
        return token;
    }

    /**
     * 验证签名
     * @param t
     * @param sign
     * @param <T>
     */
    public static <T> void checkSign(T t,String sign){

//        String newSign = sha1(JSON.toJSONString(t));
//        if (StringUtils.isEmpty(sign) || !sign.equals(newSign)){
//            log.error("签名验证失败 genOrderRequest Data :{} clientSign:{},serverSign:{}", t.toString(),sign,newSign);
//            throw new ChatException(ErrorEnum.SIGN_CHECK_ERROR);
//        }
    }

}
