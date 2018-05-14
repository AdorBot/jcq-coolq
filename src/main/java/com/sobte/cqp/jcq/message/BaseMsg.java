package com.sobte.cqp.jcq.message;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sobte on 2018/4/25.<br>
 * Time: 2018/4/25 13:01<br>
 * Email: i@sobte.me<br>
 * 酷Q消息的基类，消息的集合
 *
 * @param <E> CQ消息类型
 * @author Sobte
 */
public abstract class BaseMsg<E extends ActionMsg> extends ArrayList<E> {

    /**
     * 获取相匹配的 CQ消息
     *
     * @param e 要匹配的消息
     * @return CQ消息
     */
    public E get(E e) {
        int index = indexOf(e);
        return index == -1 ? null : get(index);
    }

    /**
     * 修改CQ消息，不存在则添加至末尾(覆盖)
     *
     * @param e CQ消息
     * @return 覆盖前CQ消息
     */
    public E set(E e) {
        int index = indexOf(e);
        if (index == -1)
            add(e);
        else
            set(index, e);
        return e;
    }

    /**
     * 获取整段的消息文本
     *
     * @return 消息文本
     */
    public String msg() {
        Iterator<E> it = iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext())
            sb.append(it.next());
        return sb.toString();
    }

}
