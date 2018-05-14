package com.sobte.cqp.jcq.message;

/**
 * Created by Sobte on 2018/4/24.<br>
 * Time: 2018/4/24 19:09<br>
 * Email: i@sobte.me<br>
 * 普通接收到的消息
 *
 * @author Sobte
 */
public class ActionMsg {

    private String msg;
    /**
     * 第一次访问
     */
    protected boolean first;

    public ActionMsg() {
    }

    public ActionMsg(String msg) {
        this.msg = msg;
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
