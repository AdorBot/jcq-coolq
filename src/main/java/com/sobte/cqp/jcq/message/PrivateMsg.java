package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.EventType;

/**
 * Created by Sobte on 2018/10/21.<br>
 * Time: 2018/10/21 23:13<br>
 * Email: i@sobte.me<br>
 * 私聊
 *
 * @author Sobte
 */
public class PrivateMsg extends Sender {

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param msg   消息
     * @param msgId 消息ID
     * @param qqId  QQ号
     */
    public PrivateMsg(String msg, int msgId, long qqId) {
        this(EventType.PrivateMsg, msg, msgId, qqId, true);
    }

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param msg   消息
     * @param msgId 消息ID
     * @param qqId  QQ号
     * @param load  是否初始加载信息
     */
    public PrivateMsg(String msg, int msgId, long qqId, boolean load) {
        this(EventType.PrivateMsg, msg, msgId, qqId, load);
    }

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param type  来源事件类型
     * @param msg   消息
     * @param msgId 消息ID
     * @param qqId  QQ号
     */
    public PrivateMsg(EventType type, String msg, int msgId, long qqId) {
        this(type, msg, msgId, qqId, true);
    }

    /**
     * 有参构造函数
     *
     * @param type  来源事件类型
     * @param msg   消息
     * @param msgId 消息ID
     * @param qqId  QQ号
     * @param load  是否初始加载信息
     */
    public PrivateMsg(EventType type, String msg, int msgId, long qqId, boolean load) {
        super(type, msg, msgId, qqId, load);
    }

    /**
     * 有参构造函数
     *
     * @param msg  酷Q消息
     * @param load 是否初始加载信息
     */
    public PrivateMsg(CQMsg msg, boolean load) {
        super(msg, load);
    }

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param msg 酷Q消息
     */
    public PrivateMsg(CQMsg msg) {
        this(msg, true);
    }

    /**
     * 撤回消息
     *
     * @param msgId 消息ID
     * @return 本发送者对象
     */
    @Override
    public PrivateMsg deleteMsg(long msgId) {
        super.deleteMsg(msgId);
        return this;
    }

    /**
     * 撤回消息,当前发送者发送的消息
     *
     * @return 本发送者对象
     * @see #deleteMsg(long) 撤回指定消息
     */
    @Override
    public PrivateMsg deleteMsg() {
        super.deleteMsg();
        return this;
    }

    /**
     * 向当前发送者,发送手机赞,多次
     *
     * @param times 赞的次数,最多10次
     * @return 本发送者对象
     */
    @Override
    public PrivateMsg sendLike(int times) {
        super.sendLike(times);
        return this;
    }

    /**
     * 向当前发送者,发送手机赞,赞一次
     *
     * @return 本发送者对象
     * @see #sendLike(int) 发送多次手机赞
     */
    @Override
    public PrivateMsg sendLike() {
        super.sendLike();
        return this;
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 本发送者对象
     */
    @Override
    public PrivateMsg refresh(long qqId, boolean notCache) {
        super.refresh(qqId, notCache);
        return this;
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param qqId QQ号
     * @return 本发送者对象
     */
    @Override
    public PrivateMsg refresh(long qqId) {
        super.refresh(qqId);
        return this;
    }

    /**
     * 刷新本对象成员信息
     *
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 本发送者对象
     */
    @Override
    public PrivateMsg refresh(boolean notCache) {
        super.refresh(notCache);
        return this;
    }

    /**
     * 刷新本对象成员信息
     *
     * @return 本发送者对象
     */
    @Override
    public PrivateMsg refresh() {
        super.refresh();
        return this;
    }
}
