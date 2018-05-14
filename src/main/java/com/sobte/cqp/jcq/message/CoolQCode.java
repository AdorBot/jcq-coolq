package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.util.StringHelper;

import java.util.*;

/**
 * Created by Sobte on 2018/4/11.<br>
 * Time: 2018/4/11 0:26<br>
 * Email: i@sobte.me<br>
 * 酷Q码类，用于解析和合并酷Q码，酷Q码集合
 *
 * @author Sobte
 */
public class CoolQCode extends BaseMsg<ActionCode> {

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
        int length = code.length();
        for (int i = 0; i < length; i++) {
            switch (code.charAt(i)) {
                case '[':
                    int idx = code.indexOf(']', i);
                    if (idx == -1) {
                        i = length;// 设置为超出，则退出循环
                        continue;
                    }
                    if (code.charAt(++i) == 'C' && code.charAt(++i) == 'Q' && code.charAt(++i) == ':') {
                        int endidx = code.indexOf(',', ++i);
                        if (endidx == -1 || endidx > idx)
                            endidx = idx;
                        add(ActionCode.analysis(code.substring(i, endidx), code.substring(endidx, idx)));
                        i = idx;
                    }
                    break;
            }
        }
    }

    /**
     * 获取指定功能名称的 CQ功能<br>
     * 遇到相同功能名称，则返回最先找到的 CQ功能
     *
     * @param key 功能名称
     * @return CQ功能
     */
    public ActionCode get(String key) {
        return get(new ActionCode(key));// 利用重写的equals方法进行判定
    }


    /**
     * 获取指定功能名称的 CQ功能 中键的值<br>
     *
     * @param key       功能名称
     * @param actionKey 功能中的键
     * @return 功能中键的值，不存在返回 {@code null}
     */
    public String get(String key, String actionKey) {
        int index = indexOf(new ActionCode(key));// 利用重写的equals方法进行判定
        if (index == -1)
            return null;
        ActionCode ac = get(index);
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
        int index = indexOf(new ActionCode(key));// 利用重写的equals方法进行判定
        if (index == -1)
            return null;
        ActionCode ac = get(index);
        return ac.get(actionIndex);
    }

    /**
     * 获取指定功能名称的 所有CQ功能<br>
     * 遇到相同功能名称，则返回 符合的所有CQ功能
     *
     * @param key 功能名称
     * @return CQ功能
     */
    public List<ActionCode> gets(String key) {
        Iterator<ActionCode> it = iterator();
        List<ActionCode> list = new ArrayList<ActionCode>();
        while (it.hasNext()) {
            ActionCode ac = it.next();
            if (ac.getAction().equals(key))
                list.add(ac);
        }
        return list;
    }

    /**
     * 获取指定功能名称所有CQ功能中指定键的值<br>
     *
     * @param key       功能名称
     * @param actionKey 功能中的键
     * @return 所有CQ功能中键的值
     */
    public List<String> gets(String key, String actionKey) {
        Iterator<ActionCode> it = iterator();
        List<String> values = new ArrayList<String>();
        while (it.hasNext()) {
            ActionCode ac = it.next();
            if (ac.getAction().equals(key))
                values.add(ac.get(actionKey));
        }
        return values;
    }


    /**
     * 添加CQ功能(添加)
     *
     * @param key 功能名称
     * @return 是否成功
     */
    public boolean add(String key) {
        return add(new ActionCode(key));
    }

    /**
     * 添加CQ功能(添加)
     *
     * @param key       功能名称
     * @param actionKey 功能中的键
     * @param value     功能中的值
     * @return 是否成功
     */
    public boolean add(String key, String actionKey, String value) {
        ActionCode ac = new ActionCode(key);
        ac.put(actionKey, value);
        return add(ac);
    }

    /**
     * 修改CQ功能，不存在则添加(覆盖)
     *
     * @param key 功能名称
     * @return 覆盖前CQ功能
     */
    public ActionCode set(String key) {
        return set(new ActionCode(key));// 调用
    }

    /**
     * 删除指定功能名称的CQ功能
     *
     * @param key 功能名称
     * @return 是否成功
     */
    public boolean remove(String key) {
        return remove(new ActionCode(key));
    }

}
