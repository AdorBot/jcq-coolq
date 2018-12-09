package com.sobte.cqp.jcq.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Sobte on 2016/11/9.
 * 字符串帮助类
 */
public class StringUtils {

    /**
     * 系统换行符，根据不同的系统返回不同的换行符(字符串)
     */
    public static final String lineSeparator = System.getProperty("line.separator");

    /**
     * 判断是否为空
     *
     * @param args 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String... args) {
        if (args == null || args.length == 0)
            return true;
        for (String string : args)
            if (string == null || string.length() == 0)
                return true;
        return false;
    }

    /**
     * 获取单字符的换行符 (Windows下则将\r\n 变为\n)
     *
     * @return 换行符
     */
    public static char lineSeparatorSingle() {
        // 获取单字符换行符
        int len = lineSeparator.length();
        if (len == 2 || len == 0)
            return '\n';
        else
            return lineSeparator.charAt(0);
    }

    /**
     * 去前后空格后判断是否为空
     *
     * @param args 字符串
     * @return 是否为空
     */
    public static boolean isTrimEmpty(String... args) {
        if (args == null || args.length == 0)
            return true;
        for (String string : args)
            if (string == null || string.trim().length() == 0)
                return true;
        return false;
    }

    /**
     * 去除左边空格
     *
     * @param string 字符串
     * @return 修改后的字符串
     */
    public static String trimLeft(String string) {
        char[] val = string.toCharArray();
        int len = val.length;
        int st = 0;
        while ((st < len) && (val[st] <= ' '))
            st++;
        return st > 0 ? string.substring(st, len) : string;
    }

    /**
     * 去除右边空格
     *
     * @param string 字符串
     * @return 修改后的字符串
     */
    public static String trimRight(String string) {
        char[] val = string.toCharArray();
        int len = val.length;
        int st = 0;
        while ((st < len) && (val[len - 1] <= ' '))
            len--;
        return len < val.length ? string.substring(st, len) : string;
    }

    /**
     * 去除左边空格
     *
     * @param string 字符串
     * @return 修改后的字符串
     */
    public static String trimEnter(String string) {
        char[] val = string.toCharArray();
        int len = val.length;
        int st = 0;
        while ((st < len) && (val[st] < ' '))
            st++;
        while ((st < len) && (val[len - 1] < ' '))
            len--;
        return st > 0 ? string.substring(st, len) : string;
    }

    /**
     * 去除左边空格
     *
     * @param string 字符串
     * @return 修改后的字符串
     */
    public static String trimEnterLeft(String string) {
        char[] val = string.toCharArray();
        int len = val.length;
        int st = 0;
        while ((st < len) && (val[st] < ' '))
            st++;
        return st > 0 ? string.substring(st, len) : string;
    }

    /**
     * 去除右边空格
     *
     * @param string 字符串
     * @return 修改后的字符串
     */
    public static String trimEnterRight(String string) {
        char[] val = string.toCharArray();
        int len = val.length;
        int st = 0;
        while ((st < len) && (val[len - 1] < ' '))
            len--;
        return len < val.length ? string.substring(st, len) : string;
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
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            unicode.append("\\u");
            unicode.append(Integer.toHexString(string.charAt(i)));
        }
        return unicode.toString();
    }

    /**
     * unicode 转字符串
     *
     * @param unicode 原Unicode字符
     * @return 字符串
     */
    public static String unicodeToString(String unicode) {
        StringBuilder string = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
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
     * 字符串替换,替换第一个匹配的相同的字符串
     *
     * @param src         源字符串
     * @param target      被替换的字符串
     * @param replacement 替换的字符串
     * @return 处理后的字符串
     */
    public static String stringReplaceFirst(String src, String target, String replacement) {
        if (src == null || target == null || target.length() == 0 || replacement == null)
            return src;
        int index = src.indexOf(target);
        if (index == -1)
            return src;
        int pos = 0;
        char[] rs = new char[src.length() - target.length() + replacement.length()];
        for (int i = 0; i < src.length(); i++) {
            if (i == index) {
                for (int j = 0; j < replacement.length(); j++) {
                    rs[pos] = replacement.charAt(j);
                    pos++;
                }
                i += target.length() - 1;
                continue;
            }
            rs[pos] = src.charAt(i);
            pos++;
        }
        return new String(rs);
    }

    /**
     * 字符串替换,替换所有相同的字符串
     *
     * @param src         源字符串
     * @param target      被替换的字符串
     * @param replacement 替换的字符串
     * @return 处理后的字符串
     */
    public static String stringReplace(String src, String target, String replacement) {
        if (src == null || target == null || target.length() == 0 || replacement == null)
            return src;
        int index = src.indexOf(target);
        if (index == -1)
            return src;
        StringBuilder sb = new StringBuilder();
        char c = target.charAt(0);
        for (int i = 0; i < src.length(); i++) {
            if (i >= index && src.charAt(i) == c) {
                int length = i + target.length(), pos = 0, end = 0;
                for (int j = i; j < length; j++) {
                    if (src.charAt(j) != target.charAt(pos)) {
                        end = j;
                        break;
                    }
                    pos++;
                }
                if (end != 0) {
                    for (; i < end; i++)
                        sb.append(src.charAt(i));
                    i = end - 1;
                } else {
                    for (int j = 0; j < replacement.length(); j++)
                        sb.append(replacement.charAt(j));
                    i = length - 1;
                }
                continue;
            }
            sb.append(src.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 字符串替换,替换所有相同的字符串
     *
     * @param src         源字符串
     * @param target      被替换的字符串
     * @param replacement 替换的对象（会转成字符串）
     * @return 处理后的字符串
     */
    public static String stringReplace(String src, String target, Object... replacement) {
        if (src == null || target == null || target.length() == 0 || replacement == null || replacement.length == 0)
            return src;
        int index = src.indexOf(target), repIdx = 0;
        if (index == -1)
            return src;
        StringBuilder sb = new StringBuilder();
        char c = target.charAt(0);
        for (int i = 0; i < src.length(); i++) {
            if (i >= index && src.charAt(i) == c && repIdx < replacement.length) {
                int length = i + target.length(), pos = 0, end = 0;
                for (int j = i; j < length; j++) {
                    if (src.charAt(j) != target.charAt(pos)) {
                        end = j;
                        break;
                    }
                    pos++;
                }
                if (end != 0) {
                    for (; i < end; i++)
                        sb.append(src.charAt(i));
                    i = end - 1;
                } else {
                    if (replacement[repIdx] != null) {
                        String replaceStr = replacement[repIdx].toString();
                        for (int j = 0; j < replaceStr.length(); j++)
                            sb.append(replaceStr.charAt(j));
                        i = length - 1;
                    }
                    repIdx++;
                }
                continue;
            }
            sb.append(src.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 获取异常打印的 堆栈信息
     *
     * @param e 异常
     * @return 堆栈信息
     */
    public static String getStackTracePrintString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        pw.close();
        return sw.toString();
    }

    public static String stringPrefix(String src, String split) {
        if (isEmpty(src))
            return src;
        if (isEmpty(split))
            return split;
        return src.substring(0, src.indexOf(split));
    }

    public static String stringPrefix(String src, int split) {
        if (isEmpty(src))
            return src;
        return src.substring(0, src.indexOf(split));
    }

    public static String stringSuffix(String src, String split) {
        if (isEmpty(src))
            return src;
        if (isEmpty(split))
            return split;
        return src.substring(src.indexOf(split));
    }

    public static String stringSuffix(String src, int split) {
        if (isEmpty(src))
            return src;
        return src.substring(src.indexOf(split));
    }

    public static String stringLastPrefix(String src, String split) {
        if (isEmpty(src))
            return src;
        if (isEmpty(split))
            return split;
        return src.substring(0, src.lastIndexOf(split));
    }

    public static String stringLastPrefix(String src, int split) {
        if (isEmpty(src))
            return src;
        return src.substring(0, src.lastIndexOf(split));
    }

    public static String stringLastSuffix(String src, String split) {
        if (isEmpty(src))
            return src;
        if (isEmpty(split))
            return split;
        return src.substring(src.lastIndexOf(split));
    }

    public static String stringLastSuffix(String src, int split) {
        if (isEmpty(src))
            return src;
        return src.substring(src.lastIndexOf(split));
    }

}
