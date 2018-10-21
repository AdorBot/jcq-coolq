package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.EventType;
import com.sobte.cqp.jcq.annotation.Gender;
import com.sobte.cqp.jcq.entity.QQInfo;

/**
 * Created by Sobte on 2018/7/14.<br>
 * Time: 2018/7/14 23:15<br>
 * Email: i@sobte.me<br>
 *
 * @author Sobte
 */
public class Sender extends AbstractSender {

    /**
     * QQ信息
     */
    private final QQInfo info;

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param type  来源事件类型
     * @param msg   消息
     * @param msgId 消息ID
     * @param qqId  QQ号
     */
    public Sender(EventType type, String msg, int msgId, long qqId) {
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
    public Sender(EventType type, String msg, int msgId, long qqId, boolean load) {
        this(type, msg, msgId, new QQInfo(qqId));
        if (load) refresh();
    }

    /**
     * 有参构造函数
     *
     * @param msg  酷Q消息
     * @param load 是否初始加载信息
     */
    public Sender(CQMsg msg, boolean load) {
        this(msg, new QQInfo(msg.getFromQQ()));
        if (load) refresh();
    }

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param msg 酷Q消息
     */
    public Sender(CQMsg msg) {
        this(msg, true);
    }

    /**
     * 有参构造函数
     *
     * @param type  来源事件类型
     * @param msg   消息
     * @param msgId 消息ID
     * @param info  初始QQ信息
     */
    Sender(EventType type, String msg, int msgId, QQInfo info) {
        super(type, msg, msgId);
        this.info = info;
    }

    /**
     * 有参构造函数
     *
     * @param msg  酷Q消息
     * @param info 初始QQ信息
     */
    Sender(CQMsg msg, QQInfo info) {
        super(msg);
        this.info = info;
    }

    /**
     * 获取QQ号
     *
     * @return QQ号
     */
    @Override
    public long getQQId() {
        return info.getQQId();
    }

    /**
     * 获取性别
     *
     * @return 性别
     */
    @Override
    public Gender getGender() {
        return Gender.valueOf(info.getGender());
    }

    /**
     * 获取年龄
     *
     * @return 年龄
     */
    @Override
    public int getAge() {
        return info.getAge();
    }

    /**
     * 获取昵称
     *
     * @return 昵称
     */
    @Override
    public String getNick() {
        return info.getNick();
    }

    /**
     * 撤回消息
     *
     * @param msgId 消息ID
     * @return 本发送者对象
     */
    @Override
    public Sender deleteMsg(long msgId) {
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
    public Sender deleteMsg() {
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
    public Sender sendLike(int times) {
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
    public Sender sendLike() {
        super.sendLike();
        return this;
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 是否刷新成功
     */
    @Override
    protected boolean refreshInfo(long qqId, boolean notCache) {
        return CQ.getStrangerInfo(info, qqId, notCache) != null;
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 本发送者对象
     */
    @Override
    public Sender refresh(long qqId, boolean notCache) {
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
    public Sender refresh(long qqId) {
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
    public Sender refresh(boolean notCache) {
        super.refresh(notCache);
        return this;
    }

    /**
     * 刷新本对象成员信息
     *
     * @return 本发送者对象
     */
    @Override
    public Sender refresh() {
        super.refresh();
        return this;
    }

}
