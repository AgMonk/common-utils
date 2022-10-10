package org.gin;

import java.io.Serializable;
import java.util.UUID;

/**
 * 字符串工具类
 * @author bx002
 */
@SuppressWarnings("unused")
public class StringUtils {
    /**
     * 判断一个对象是否为空
     * @param o 对象
     * @return 是否为空
     */
    public static boolean isEmpty(Serializable o) {
        return o == null || "".equals(o);
    }
    /**
     * 判断一个对象是否为非空
     * @param o 对象
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Serializable o) {
        return !isEmpty(o);
    }

    /**
     * 生成指定段数的随机uuid
     * @param count 段数
     * @return 随机uuid
     */
    public static String createUuid(int count) {
        final String[] split = UUID.randomUUID().toString().split("-");
        int c = Math.min(count, split.length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c; i++) {
            sb.append(split[i]);
            if (i < c - 1) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

}
