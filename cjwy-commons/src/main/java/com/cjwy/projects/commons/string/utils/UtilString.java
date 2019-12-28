package com.cjwy.projects.commons.string.utils;

import java.util.UUID;

/**
 * 字符串工具类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 9:44 下午
 */
public class UtilString {

    private UtilString() {

    }

    /**
     * 获取UUID
     *
     * @return 返回不带连字符的UUID
     */
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 获取UUID
     *
     * @return 返回带连字符的UUID
     */
    public static String randomUUIDWithHyphen() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
