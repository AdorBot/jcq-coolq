package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.EventType;

/**
 * Created by Sobte on 2018/10/21.<br>
 * Time: 2018/10/21 23:17<br>
 * Email: i@sobte.me<br>
 *
 * @author Sobte
 */
public class DiscussMsg extends Sender {

    private final long discussionId;

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param msg          消息
     * @param msgId        消息ID
     * @param discussionId 讨论组ID
     * @param qqId         QQ号
     */
    public DiscussMsg(String msg, int msgId, long discussionId, long qqId) {
        this(msg, msgId, discussionId, qqId, true);
    }

    /**
     * 有参构造函数
     *
     * @param msg          消息
     * @param msgId        消息ID
     * @param discussionId 讨论组ID
     * @param qqId         QQ号
     * @param load         是否初始加载信息
     */
    public DiscussMsg(String msg, int msgId, long discussionId, long qqId, boolean load) {
        this(EventType.DiscussMsg, msg, msgId, discussionId, qqId, load);
    }

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param type         来源事件类型
     * @param msg          消息
     * @param msgId        消息ID
     * @param discussionId 讨论组ID
     * @param qqId         QQ号
     */
    public DiscussMsg(EventType type, String msg, int msgId, long discussionId, long qqId) {
        this(type, msg, msgId, discussionId, qqId, true);
    }

    /**
     * 有参构造函数
     *
     * @param type         来源事件类型
     * @param msg          消息
     * @param msgId        消息ID
     * @param discussionId 讨论组ID
     * @param qqId         QQ号
     * @param load         是否初始加载信息
     */
    public DiscussMsg(EventType type, String msg, int msgId, long discussionId, long qqId, boolean load) {
        super(type, msg, msgId, qqId, load);
        this.discussionId = discussionId;
    }

    /**
     * 有参构造函数
     *
     * @param msg  酷Q消息
     * @param load 是否初始加载信息
     */
    public DiscussMsg(CQMsg msg, boolean load) {
        super(msg, load);
        this.discussionId = msg.getFromId();
    }

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param msg 酷Q消息
     */
    public DiscussMsg(CQMsg msg) {
        this(msg, true);
    }

    /**
     * 获取讨论组ID
     *
     * @return 讨论组ID
     */
    public long getDiscussionId() {
        return discussionId;
    }

    /**
     * 撤回消息
     *
     * @param msgId 消息ID
     * @return 本发送者对象
     */
    @Override
    public DiscussMsg deleteMsg(long msgId) {
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
    public DiscussMsg deleteMsg() {
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
    public DiscussMsg sendLike(int times) {
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
    public DiscussMsg sendLike() {
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
    public DiscussMsg refresh(long qqId, boolean notCache) {
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
    public DiscussMsg refresh(long qqId) {
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
    public DiscussMsg refresh(boolean notCache) {
        super.refresh(notCache);
        return this;
    }

    /**
     * 刷新本对象成员信息
     *
     * @return 本发送者对象
     */
    @Override
    public DiscussMsg refresh() {
        super.refresh();
        return this;
    }

    /**
     * 向当前讨论组,发送讨论组消息,不立即发送,创建消息处理对象
     *
     * @return 消息处理对象
     */
    public MsgBuilder sendGroupMsg() {
        return CQ.sendDiscussMsg(getDiscussionId());
    }

    /**
     * 向当前讨论组,发送讨论组消息
     *
     * @param msg 消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    public int sendGroupMsg(String msg) {
        return status = CQ.sendDiscussMsg(getDiscussionId(), msg);
    }
}
