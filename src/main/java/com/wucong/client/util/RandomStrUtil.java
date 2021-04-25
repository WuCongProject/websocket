package com.wucong.client.util;

import java.util.Random;

/**
 * @Author: kerwin
 * @Date: 2020/10/3 14:55
 * @Description:
 */
public class RandomStrUtil {

    /**
     * 字符数组
     */
    private static String CHAR_ARRAY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890{}[]|:<>?!@#$%^&*,./";

    private static String STR_ARRAY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    /**
     * 字母数组
     */
    private static String LETTER_ARRAY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 获取一个随机随机字符串
     * @param length
     * @return
     */
    public static String getStr(Integer length) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0;i<length;i++){
            //获取随机字符
            stringBuffer.append(CHAR_ARRAY.charAt(random.nextInt(CHAR_ARRAY.length())));
        }
        return stringBuffer.toString();
    }

    public static String getInt(Integer length){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0;i<length;i++){
            //获取随机数字
            stringBuffer.append(random.nextInt(9));
        }
        return stringBuffer.toString();
    }

    /**
     * 生成随机名
     * @param length
     * @return
     */
    public static String getName(Integer length){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0;i<length;i++){
            //获取随机字符
            stringBuffer.append(LETTER_ARRAY.charAt(random.nextInt(LETTER_ARRAY.length())));
        }
        return stringBuffer.toString();
    }

}