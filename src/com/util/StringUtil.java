package com.util;

/**
 * �ַ���������
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StringUtil {
    /**
     * �ж��Ƿ���Ϊ��
     *
     * @param str �ַ���
     * @return ����ֵ
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * �ж��Ƿ��ǿ�
     *
     * @param str �ַ���
     * @return ����ֵ
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
