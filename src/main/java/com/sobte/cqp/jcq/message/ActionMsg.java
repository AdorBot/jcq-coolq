package com.sobte.cqp.jcq.message;

import java.io.Serializable;

/**
 * Created by Sobte on 2018/4/24.<br>
 * Time: 2018/4/24 19:09<br>
 * Email: i@sobte.me<br>
 * 普通接收到的消息
 *
 * @author Sobte
 */
public class ActionMsg implements Serializable {

    private static final long serialVersionUID = 7333012650050787773L;

    /**
     * 消息
     */
    private String msg;
    /**
     * 第一次访问
     */
    protected boolean first;

    /**
     * 无参构造函数
     */
    public ActionMsg() {
    }

    /**
     * 有参构造函数
     *
     * @param msg 消息
     */
    public ActionMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 有参构造函数
     *
     * @param msg         消息
     * @param firstDecode 首次访问是否解码
     */
    public ActionMsg(String msg, boolean firstDecode) {
        this.msg = msg;
        this.first = !firstDecode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ActionMsg) {
            ActionMsg am = (ActionMsg) obj;
            return am.getMsg() != null && am.getMsg().equals(this.msg);
        }
        return false;
    }

    public String getMsg() {
        if (!first) {
            msg = CQCode.decode(msg);
            first = true;
        }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return CQCode.encode(msg, true);
    }

}
