package com.wucong.client.util;

import com.wucong.client.enums.ErrorEnum;
import com.wucong.client.exception.ChatException;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: kerwin
 * @Date: 2020/10/16 16:48
 * @Description:
 */
public class IdUtil {

    /**
     * 使用单例模式，不允许直接创建实例
     */
    private IdUtil() {}
    /**
     * 创建一个空实例对象，类需要用的时候才赋值
     */
    private static IdUtil instance = null;
    /**
     * 单例模式--懒汉模式 保证高并发下id不冲突
     */
    public static synchronized IdUtil getInstance() {
        if (instance == null) {
            instance = new IdUtil();
        }
        return instance;
    }

    /**
     * 平台 id尾号
     */
    private static volatile int platCount = 1;

    /**
     * 格式化的时间字符串
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 定义锁对象
     */
    private final static ReentrantLock lock = new ReentrantLock();

    /**
     *  订单尾号最大值
     */
    private final static int Max_Id_Val = 99;

    /**
     * 获取当前时间年月日时分秒毫秒字符串
     */
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }
    /**
     * @return
     */
    public static String getId(){
        String id;
        //加锁
        lock.lock();
        //判断时间是否相同
        try {
            if (platCount >= Max_Id_Val){
                platCount = 1;
            }
            //拼接 Id = 头信息 + 年月日时分秒 + 时间戳 + 尾号
            id = getNowDateStr() + System.currentTimeMillis()/1000 + String.format("%02d",platCount);
            platCount++;
        } catch (Exception e) {
            //抛出异常
            throw new ChatException(ErrorEnum.ID_CREATE_ERROR);
        }finally{
            lock.unlock();
        }
        return id;
    }
    /**
     * 商品订单编号
     * @param head 订单ID头信息
     * @return
     */
    public static String getId(String head){
        String id;

        SecureRandom random = new SecureRandom();
        //加锁
        lock.lock();
        //判断时间是否相同
        try {
            if (platCount >= Max_Id_Val){
                platCount = 1;
            }
            //拼接 Id = 头信息 + 时间戳 + 尾号
            id = head + getNowDateStr()+ String.format("%02d",random.nextInt(99)) + String.format("%02d",platCount);
            platCount++;
        } catch (Exception e) {
            //抛出异常
            throw new ChatException(ErrorEnum.ID_CREATE_ERROR);
        }finally{
            lock.unlock();
        }
        return id;
    }
}