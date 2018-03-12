package com.sobte.cqp.jcq.util;

/**
 * Created by Sobte on 2016/11/9.
 * 字符串帮助类
 */
public class StringHelper {

    /**
     * 判断是否为空
     *
     * @param args 字符串
     * @return 是否为空
     */
    public static boolean isTrimEmpty(String... args) {
        if (args == null || args.length == 0) {
            return true;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null || args[i].length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象到字符串
     *
     * @param obj 对象
     * @return 字符串
     */
    public static String objToString(Object obj) {
        return (obj == null) ? null : obj.toString();
    }

    /**
     * 字符串转换unicode
     *
     * @param string 原字符串
     * @return unicode字符串
     */
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * 拼接字符串
     *
     * @param args 要拼接的对象
     * @return 字符串
     */
    public static String stringConcat(Object... args) {
        StringBuilder sb = new StringBuilder();
        if (args != null) {
            for (Object obj : args) {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    /**
     * unicode 转字符串
     *
     * @param unicode 原Unicode字符
     * @return 字符串
     */
    public static String unicodeToString(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

}
