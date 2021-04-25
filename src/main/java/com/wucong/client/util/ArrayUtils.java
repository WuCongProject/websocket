package com.wucong.client.util;

import java.util.Collection;

/**
 * @Author: kerwin
 * @Date: 2020/10/20 17:12
 * @Description:
 */
public class ArrayUtils {

    public static <T> boolean isNotEmpty(Collection<T> list){
        if (list == null || list.size()<=0){
            return true;
        }
        return false;
    }

}
