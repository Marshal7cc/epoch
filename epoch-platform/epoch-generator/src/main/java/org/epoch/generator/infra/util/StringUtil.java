package org.epoch.generator.infra.util;

/**
 * @author Marshal
 * @date 2019/5/19
 */
public class StringUtil {
    private static final char UNDERLINE = '_';

    /**
     * 下划线 转 驼峰
     *
     * @param tableName
     * @return
     */
    public static String getBeanName(String tableName) {
        if (tableName == null || "".equals(tableName.trim())) {
            return "";
        }
        int len = tableName.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = Character.toLowerCase(tableName.charAt(i));
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(tableName.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString().substring(0, 1).toUpperCase() + sb.toString().substring(1);
    }
}


