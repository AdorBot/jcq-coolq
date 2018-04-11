package com.sobte.cqp.jcq.entity;

import com.sobte.cqp.jcq.util.StringHelper;

import java.util.*;

/**
 * Created by Sobte on 2018/4/11.<br>
 * Time: 2018/4/11 0:26<br>
 * Email: i@sobte.me<br>
 * 酷Q码类，用于解析和合并酷Q码，支持多线程(线程安全)
 *
 * @author Sobte
 */
public class CoolQCode {

    /**
     * CQ码的集合
     */
    private List<ActionCode> list = Collections.synchronizedList(new ArrayList<ActionCode>());

    /**
     * 无参初始化
     */
    public CoolQCode() {

    }

    /**
     * 初始化
     *
     * @param code 要解析的CQ码
     */
    public CoolQCode(String code) {
        analysis(code);
    }

    /**
     * 解析CQ码
     *
     * @param code 要解析的 酷Q码
     */
    public void analysis(String code) {
        if (StringHelper.isTrimEmpty(code))
            return;
        char[] chars = code.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '[':
                    int idx = code.indexOf(']', i);
                    if (idx == -1) {
                        i = chars.length;// 设置为超出，则退出循环
                        continue;
                    }
                    if (!new String(chars, i + 1, 3).equals("CQ:"))// 排除不是CQ码的文本
                        continue;
                    i = i + 4;// 加4是为了指CQ:后面
                    int endidx = code.indexOf(',', i);
                    if (endidx == -1)
                        endidx = idx;
                    analysisKeyValue(code.substring(i, endidx), code.substring(endidx, idx));
                    break;
            }
        }
    }

    /**
     * 解析文本并添加到对应的 CQ功能 中
     *
     * @param action 功能名称
     * @param code   要解析的文本
     */
    public void analysisKeyValue(String action, String code) {
        ActionCode ac = new ActionCode();
        ac.setAction(CQCode.decode(action));
        char[] chars = code.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '=':
                    int idx = code.lastIndexOf(',', i) + 1;
                    String key = code.substring(idx, i);
                    idx = code.indexOf(',', i);
                    if (idx == -1)
                        idx = chars.length;
                    String value = code.substring(i + 1, idx);// 加1排除 '='
                    ac.put(CQCode.decode(key), CQCode.decode(value));
                    i = idx;
                    break;
            }
        }
        list.add(ac);
    }

    /**
     * 获取指定位置的 CQ功能
     *
     * @param index 下标
     * @return CQ功能
     */
    public ActionCode get(int index) {
        return list.get(index);
    }

    /**
     * 获取指定功能名称的 CQ功能<br>
     * 遇到相同功能名称，则返回最先找到的 CQ功能
     *
     * @param key 功能名称
     * @return CQ功能
     */
    public ActionCode get(String key) {
        int index = list.indexOf(new ActionCode(key));// 利用重写的equals方法进行判定
        return index == -1 ? null : list.get(index);
    }

    /**
     * 获取指定功能名称的 CQ功能<br>
     * 遇到相同功能名称，则返回最先找到的 CQ功能
     *
     * @param ac 含有指定功能名称的CQ功能
     * @return CQ功能
     */
    public ActionCode get(ActionCode ac) {
        int index = list.indexOf(ac);// 利用重写的equals方法进行判定
        return index == -1 ? null : list.get(index);
    }

    /**
     * 获取指定功能名称的 CQ功能 中键的值<br>
     *
     * @param key       功能名称
     * @param actionKey 功能中的键
     * @return 功能中键的值，不存在返回 {@code null}
     */
    public String get(String key, String actionKey) {
        int index = list.indexOf(new ActionCode(key));// 利用重写的equals方法进行判定
        if (index == -1)
            return null;
        ActionCode ac = list.get(index);
        return ac.get(actionKey);
    }

    /**
     * 获取指定功能名称的 CQ功能 中键下标的值<br>
     *
     * @param key         功能名称
     * @param actionIndex 功能中的键的下标
     * @return 功能中键的值，不存在返回 {@code null}
     */
    public String get(String key, int actionIndex) {
        int index = list.indexOf(new ActionCode(key));// 利用重写的equals方法进行判定
        if (index == -1)
            return null;
        ActionCode ac = list.get(index);
        return ac.get(actionIndex);
    }

    /**
     * 添加CQ功能(添加)
     *
     * @param key 功能名称
     * @return 是否成功
     */
    public boolean add(String key) {
        return list.add(new ActionCode(key));
    }

    /**
     * 添加CQ功能(添加)
     *
     * @param ac CQ功能
     * @return 是否成功
     */
    public boolean add(ActionCode ac) {
        return list.add(ac);
    }

    /**
     * 添加CQ功能到指定位置 (添加)
     *
     * @param ac CQ功能
     */
    public void add(int index, ActionCode ac) {
        list.add(index, ac);
    }

    /**
     * 添加CQ功能(添加)
     *
     * @param key 功能名称
     * @return 是否成功
     */
    public boolean add(String key, String actionKey, String value) {
        ActionCode ac = new ActionCode(key);
        ac.put(actionKey, value);
        return list.add(ac);
    }

    /**
     * 批量添加CQ功能(添加)
     *
     * @param c CQ功能
     * @return 是否成功
     */
    public boolean addAll(Collection<? extends ActionCode> c) {
        return list.addAll(c);
    }

    /**
     * 修改CQ功能，不存在则添加(覆盖)
     *
     * @param ac CQ功能
     * @return 是否成功
     */
    public boolean set(ActionCode ac) {
        int index = list.indexOf(ac);// 利用重写的equals方法进行判定
        if (index == -1)
            return list.add(ac);
        else {
            list.remove(index);
            list.add(index, ac);
            return true;
        }
    }

    /**
     * 修改指定下标 CQ功能，不存在则添加(覆盖)
     *
     * @param index 下标
     * @param ac    CQ功能
     * @return 是否成功
     */
    public boolean set(int index, ActionCode ac) {
        int max = list.size() - 1;
        if (index == -1 || index > max)
            return list.add(ac);
        else {
            list.remove(index);
            list.add(index, ac);
            return true;
        }
    }

    /**
     * 修改CQ功能，不存在则添加(覆盖)
     *
     * @param key 功能名称
     * @return 是否成功
     */
    public boolean set(String key) {
        return set(new ActionCode(key));// 调用
    }

    /**
     * 获取迭代器，用于遍历
     *
     * @return 迭代器
     */
    public Iterator<ActionCode> iterator() {
        return list.iterator();
    }

    /**
     * 删除指定坐标的CQ功能
     *
     * @param index 坐标
     * @return CQ功能
     */
    public ActionCode remove(int index) {
        return list.remove(index);
    }

    /**
     * 删除指定功能名称的CQ功能
     *
     * @param key 功能名称
     * @return 是否成功
     */
    public boolean remove(String key) {
        return list.remove(new ActionCode(key));
    }

    /**
     * 删除指定功能名称的CQ功能
     *
     * @param ac CQ功能
     * @return 是否成功
     */
    public boolean remove(ActionCode ac) {
        return list.remove(ac);
    }

    /**
     * 获取大小
     *
     * @return 大小
     */
    public int size() {
        return list.size();
    }

    /**
     * 清空
     */
    public void clear() {
        list.clear();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
