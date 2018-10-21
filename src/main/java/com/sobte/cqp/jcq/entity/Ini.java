package com.sobte.cqp.jcq.entity;

import com.sobte.cqp.jcq.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sobte on 2018/4/10.<br>
 * Time: 2018/4/10 13:46<br>
 * Email: i@sobte.me<br>
 * ini文本解析类，支持多线程(线程安全)
 *
 * @author Sobte
 */
public class Ini {

    /**
     * 解析后的集合
     */
    private ConcurrentHashMap<String, ConcurrentHashMap<String, List<String>>> map = new ConcurrentHashMap<String, ConcurrentHashMap<String, List<String>>>();
    /**
     * 获取单个换行字符
     */
    public static final char lineSeparator = StringUtils.lineSeparatorSingle();

    public Ini() {

    }

    /**
     * 初始化对象
     *
     * @param exp 将要解析的字符串
     */
    public Ini(String exp) {
        analysis(exp);
    }

    /**
     * 解析ini字符串的入口
     *
     * @param exp 将要解析的字符串
     */
    public void analysis(String exp) {
        // 获取单字符换行符
        int length = exp.length();
        for (int i = 0; i < length; i++) {
            switch (exp.charAt(i)) {
                // ini注释
                case ';':
                case '#':
                    i = exp.indexOf(lineSeparator, i);
                    if (i == -1)
                        i = length;// 直接设置为超出，相当于退出循环
                    break;
                // ini段落
                case '[':
                    int idx = exp.indexOf(']', i);
                    if (idx != -1) {
                        int endIdx = exp.indexOf('[', idx);
                        if (endIdx == -1)
                            endIdx = length;
                        else
                            endIdx--;
                        analysisKeyValue(exp.substring(++i, idx), endIdx < ++idx ? "" : exp.substring(idx, endIdx));// key加一排除开头 '[' , endIdx减一排除末尾 '['  , idx加一排除开头 ']'
                        i = endIdx;
                    } else {
                        i = length;// 直接设置为超出，相当于退出循环
                    }
                    break;
                case '=':
                    analysisKeyValue("", exp);
                    i = length;// 直接设置为超出，相当于退出循环
                    break;
                // 过滤无效段落
                default:
                    break;
            }
        }
    }

    /**
     * 读取配置项内容
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Default 默认值 (当项不存在或为空时返回)
     * @return 值内容
     */
    public String getProfileString(String AppName, String KeyName, String Default) {
        String value = getProfileString(AppName, KeyName);
        if (value != null)
            return value;
        return Default;
    }

    /**
     * 读取配置项内容
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @return 值内容
     */
    public String getProfileString(String AppName, String KeyName) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(AppName);
        if (expMap != null) {
            List<String> values = expMap.get(KeyName);
            if (values != null && values.size() > 0) {
                return values.get(0);
            }
        }
        return null;
    }

    /**
     * 读取配置项内容,指定第几个的内容
     *
     * @param index   下标
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @return 值内容
     */
    public String getProfileString(int index, String AppName, String KeyName) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(AppName);
        if (expMap != null) {
            List<String> values = expMap.get(KeyName);
            if (values != null && index >= 0 && index < values.size()) {
                return values.get(index);
            }
        }
        return null;
    }

    /**
     * 写入配置项内容，不存在则添加(覆盖)
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 当前对象
     */
    public Ini setProfileString(String AppName, String KeyName, String Value) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(AppName);
        if (expMap == null)
            expMap = new ConcurrentHashMap<String, List<String>>();
        List<String> values = expMap.get(KeyName);
        if (values == null)
            values = Collections.synchronizedList(new ArrayList<String>());
        if (values.size() > 0) {
            // 覆盖之前的
            values.remove(0);
            values.add(0, Value);
        } else {
            values.add(Value);
        }
        expMap.put(KeyName, values);
        map.put(AppName, expMap);
        return this;
    }

    /**
     * 写入配置项内容(添加)
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 当前对象
     */
    public Ini addProfileString(String AppName, String KeyName, String Value) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(AppName);
        if (expMap == null)
            expMap = new ConcurrentHashMap<String, List<String>>();
        List<String> values = expMap.get(KeyName);
        if (values == null)
            values = Collections.synchronizedList(new ArrayList<String>());
        values.add(Value);
        expMap.put(KeyName, values);
        map.put(AppName, expMap);
        return this;
    }

    /**
     * 写入配置项内容，指定第几个的内容，不存在则添加(覆盖)
     *
     * @param index   下标
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 当前对象
     */
    public Ini setProfileString(int index, String AppName, String KeyName, String Value) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(AppName);
        if (expMap == null)
            expMap = new ConcurrentHashMap<String, List<String>>();
        List<String> values = expMap.get(KeyName);
        if (values == null)
            values = Collections.synchronizedList(new ArrayList<String>());
        if (index >= 0 && index < values.size()) {
            // 覆盖之前的
            values.remove(index);
            values.add(index, Value);
        } else {
            values.add(Value);
        }
        expMap.put(KeyName, values);
        map.put(AppName, expMap);
        return this;
    }

    /**
     * 写入配置项内容，指定第几个的内容(添加)
     *
     * @param index   下标
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @param Value   配置项值
     * @return 当前对象
     */
    public Ini addProfileString(int index, String AppName, String KeyName, String Value) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(AppName);
        if (expMap == null)
            expMap = new ConcurrentHashMap<String, List<String>>();
        List<String> values = expMap.get(KeyName);
        if (values == null)
            values = Collections.synchronizedList(new ArrayList<String>());
        if (index >= 0 && index < values.size())
            values.add(index, Value);
        else
            values.add(Value);
        expMap.put(KeyName, values);
        map.put(AppName, expMap);
        return this;
    }

    /**
     * 获取所有配置节名称
     *
     * @return 节名称集合
     */
    public Set<String> getAppNames() {
        return map.keySet();
    }

    /**
     * 获取某节名称下所有的配置项名
     *
     * @param AppName 节名称
     * @return 配置项名称集合
     */
    public Set<String> getKeyNames(String AppName) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(AppName);
        if (expMap == null)
            return new HashSet<String>();
        return expMap.keySet();
    }

    /**
     * 获取某节名称下某个的配置项名的所有值
     *
     * @param AppName 节名称
     * @param KeyName 配置项名
     * @return 配置项名的所有值的集合
     */
    public List<String> getValues(String AppName, String KeyName) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(AppName);
        if (expMap == null)
            return Collections.synchronizedList(new ArrayList<String>());
        List<String> values = expMap.get(KeyName);
        if (values == null)
            return Collections.synchronizedList(new ArrayList<String>());
        return values;
    }

    /**
     * 清空
     */
    public void clear() {
        map.clear();
    }

    /**
     * 获取配置节名称数量
     *
     * @return 数量
     */
    public int size() {
        return map.size();
    }

    /**
     * 解析文本并添加到对应的key中
     *
     * @param expKey 段的key
     * @param exp    要解析的文本
     */
    public void analysisKeyValue(String expKey, String exp) {
        ConcurrentHashMap<String, List<String>> expMap = map.get(expKey);
        if (expMap == null)
            expMap = new ConcurrentHashMap<String, List<String>>();
        int length = exp.length();
        for (int i = 0; i < length; i++) {
            switch (exp.charAt(i)) {
                // ini注释
                case ';':
                case '#':
                    i = exp.indexOf(lineSeparator, i);
                    if (i == -1)
                        i = length;// 直接设置为超出，相当于退出循环
                    break;
                case '=':
                    int idx = exp.lastIndexOf(lineSeparator, i);
                    if (idx == -1)
                        idx = 0;
                    String key = exp.substring(idx, i).trim();
                    idx = exp.indexOf(lineSeparator, i);
                    if (idx == -1)
                        idx = length;
                    String value = ++i > idx ? "" : StringUtils.trimEnterRight(exp.substring(i, idx));
                    // 去除value末尾换行
                    List<String> values = expMap.get(key);
                    if (values == null)
                        values = Collections.synchronizedList(new ArrayList<String>());
                    values.add(value);
                    expMap.put(key, values);
                    i = idx;
                    break;
            }
        }
        // 放置进总map中
        map.put(expKey, expMap);
    }

    /**
     * 将内部有效的ini数据转为字符串
     *
     * @return ini字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String expkey : map.keySet()) {
            sb.append('[');
            sb.append(expkey);
            sb.append(']');
            sb.append(StringUtils.lineSeparator);
            ConcurrentHashMap<String, List<String>> expMap = map.get(expkey);
            if (expMap == null)
                continue;
            for (String key : expMap.keySet()) {
                List<String> values = expMap.get(key);
                if (values == null)
                    continue;
                for (String value : values) {
                    sb.append(key);
                    sb.append('=');
                    sb.append(value);
                    sb.append(StringUtils.lineSeparator);
                }
            }
        }
        return sb.toString();
    }
}
