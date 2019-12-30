package com.cjwy.projects.commons.string.utils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 单号生成器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/31 1:21 上午
 */
public class UtilOrderNumber {

    /**
     * 每毫秒生成订单号数量最大峰值
     */
    private static final int MAX_PERMSEC_SIZE = 100;
    /**
     * 订单号生成计数器
     */
    private static long numberCount = 0L;

    private UtilOrderNumber() {
    }

    /**
     * 并发下面容易产生重复的订单号,给传入的PKID枷锁,保证资源安全的同时,性能也有所下降 订单生成策略为: 时间20180511
     * +机器编码(我这里临时填写的是00100),在本台机器上生成订单编号的标识,如果分开部署,则此处的机器码需要变更,防止出现意外重复 +二位随机数
     * +lock的hash-code编码,这里有个并发下的性能问题 +时间时分秒 +递增参数值
     *
     * @param lock 锁单号
     * @return 唯一单号
     */
    public static String makeOrderCode(String lock) {
        ReferenceQueue<StringBuilder> queue = new ReferenceQueue<>();
        WeakReference<StringBuilder> weakRef = new WeakReference<>(new StringBuilder(25), queue);
        synchronized (lock) {
            if (null == weakRef.get()) {
                weakRef = new WeakReference<>(new StringBuilder(25), queue);
            }
            // 计数器到最大值归零,目前1毫秒处理峰值1个
            if (numberCount >= MAX_PERMSEC_SIZE) {
                numberCount = 0L;
            }
            //获取秒数
            Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
            System.err.println(second);
            //获取毫秒数
            Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            System.err.println(milliSecond);
            // 取系统当前时间作为订单号变量前半部分
            weakRef.get().append(milliSecond);
            // 计数器的值
            weakRef.get().append(numberCount++);
            String uuid = weakRef.get().toString();
            // 返回15位数据,不足尾数补0
            return String.format("%-15s", uuid).replaceAll(" ", "0");
        }
    }
}
