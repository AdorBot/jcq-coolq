package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.EventType;
import com.sobte.cqp.jcq.entity.Anonymous;
import com.sobte.cqp.jcq.entity.ISystem;
import com.sobte.cqp.jcq.entity.Member;

/**
 * Created by Sobte on 2018/7/15.<br>
 * Time: 2018/7/15 11:44<br>
 * Email: i@sobte.me<br>
 * 匿名发送者
 *
 * @author Sobte
 */
public class AnonymMsg extends GroupMsg {

    /**
     * 匿名信息
     */
    public final Anonymous anonymous;

    /**
     * 源数据
     */
    private final String source;

    /**
     * 有参构造函数
     *
     * @param msg     消息
     * @param groupId 群号
     * @param source  匿名数据源
     */
    public AnonymMsg(String msg, long groupId, String source) {
        this(EventType.GroupMsg, msg, 0, new Member(groupId, ISystem.System_QQID_Anonymous), source);
    }

    /**
     * 有参构造函数
     *
     * @param type    来源事件类型
     * @param msg     消息
     * @param msgId   消息ID
     * @param groupId 群号
     * @param qqId    QQ号
     * @param source  匿名数据源
     */
    public AnonymMsg(EventType type, String msg, int msgId, long groupId, long qqId, String source) {
        this(type, msg, msgId, new Member(groupId, qqId), source);
    }

    /**
     * 有参构造函数
     *
     * @param msg    酷Q消息
     * @param source 匿名数据源
     */
    public AnonymMsg(CQMsg msg, String source) {
        this(msg, new Member(msg.getFromId(), msg.getFromQQ()), source);
    }

    /**
     * 有参构造函数
     *
     * @param type   来源事件类型
     * @param msg    消息
     * @param msgId  消息ID
     * @param member 初始成员信息
     * @param source 匿名数据源
     */
    AnonymMsg(EventType type, String msg, int msgId, Member member, String source) {
        super(type, msg, msgId, member);
        this.source = source;
        this.anonymous = CQ.getAnonymous(source);
    }

    /**
     * 有参构造函数
     *
     * @param msg    酷Q消息
     * @param member 初始成员信息
     * @param source 匿名数据源
     */
    AnonymMsg(CQMsg msg, Member member, String source) {
        super(msg, member);
        this.source = source;
        this.anonymous = CQ.getAnonymous(source);
    }

    /**
     * 获取匿名用户的标识
     *
     * @return 匿名用户的标识
     */
    public long getAid() {
        return anonymous.getAid();
    }

    /**
     * 获取代号 如“大力鬼王”
     *
     * @return 匿名代号
     */
    public String getName() {
        return anonymous.getName();
    }

    /**
     * 获取令牌
     *
     * @return 令牌
     */
    public byte[] getToken() {
        return anonymous.getToken();
    }

    /**
     * 获取匿名数据源
     *
     * @return 匿名数据源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置名片,匿名无法设置
     *
     * @param card 新名片(昵称)
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg setCard(String card) {
        super.setCard(card);
        return this;
    }

    /**
     * 设置专属头衔,需群主权限,匿名无法设置
     *
     * @param title      头衔，如果要删除，这里填空
     * @param expireTime 专属头衔有效期，单位为秒。如果永久有效，这里填写-1
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg setTitle(String title, long expireTime) {
        super.setTitle(title, expireTime);
        return this;
    }

    /**
     * 设置专属头衔,需群主权限(永久),匿名无法设置<br>
     * 如需设置时间 请调用 {@link #setTitle(String, long) setTitle(String, long)}
     *
     * @param title 头衔，如果要删除，这里填空
     * @return 本发送者对象
     * @see #setTitle(String, long) 设置专属头衔
     */
    @Override
    public AnonymMsg setTitle(String title) {
        return setTitle(title);
    }

    /**
     * 撤回消息
     *
     * @param msgId 消息ID
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg deleteMsg(long msgId) {
        super.deleteMsg(msgId);
        return this;
    }

    /**
     * 撤回消息,当前发送者发送的消息,匿名无法撤回
     *
     * @return 本发送者对象
     * @see #deleteMsg(long) 撤回指定消息
     */
    @Override
    public AnonymMsg deleteMsg() {
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
    public AnonymMsg sendLike(int times) {
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
    public AnonymMsg sendLike() {
        super.sendLike();
        return this;
    }

    /**
     * 移除此群员,匿名无法移除
     *
     * @param notBack 如果为true，则“不再接收此人加群申请”，请慎用
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg kick(boolean notBack) {
        super.kick(notBack);
        return this;
    }

    /**
     * 禁言此群员
     *
     * @param banTime 禁言的时间，单位为秒。匿名成员不支持解禁
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg ban(long banTime) {
        status = CQ.setGroupAnonymousBan(getGroupId(), source, banTime);
        return this;
    }

    /**
     * 解禁此群员,匿名无法解禁
     *
     * @return 本发送者对象
     * @see #ban(long) 禁言
     */
    @Override
    public AnonymMsg let() {
        super.let();
        return this;
    }

    /**
     * 全群禁言
     *
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg wholeBan() {
        super.wholeBan();
        return this;
    }

    /**
     * 全体解禁
     *
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg wholeLet() {
        super.wholeLet();
        return this;
    }

    /**
     * 退出此群,慎用,此接口需要严格授权<br>
     * 指当前登入QQ退出此群，移除群员请调用 {@link #kick() kick}
     *
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg leave() {
        super.leave();
        return this;
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param groupId  群号
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 是否刷新成功
     */
    @Override
    protected boolean refreshInfo(long groupId, long qqId, boolean notCache) {
        if (ISystem.System_QQID_Anonymous == qqId)
            return CQ.getStrangerInfo(member, qqId, notCache) != null;
        return super.refreshInfo(groupId, qqId, notCache);
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param groupId  群号
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg refresh(long groupId, long qqId, boolean notCache) {
        super.refresh(groupId, qqId, notCache);
        return this;
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param groupId 群号
     * @param qqId    QQ号
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg refresh(long groupId, long qqId) {
        super.refresh(groupId, qqId);
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
    public AnonymMsg refresh(long qqId, boolean notCache) {
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
    public AnonymMsg refresh(long qqId) {
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
    public AnonymMsg refresh(boolean notCache) {
        super.refresh(notCache);
        return this;
    }

    /**
     * 刷新本对象成员信息
     *
     * @return 本发送者对象
     */
    @Override
    public AnonymMsg refresh() {
        super.refresh();
        return this;
    }

}
