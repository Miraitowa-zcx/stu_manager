package com.util;

/**
 * 字符串工具类
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StringUtil {
    /**
     * 判断是否是为空
     *
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断是否不是空
     *
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
