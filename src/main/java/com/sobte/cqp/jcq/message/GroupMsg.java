package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.Authority;
import com.sobte.cqp.jcq.annotation.EventType;
import com.sobte.cqp.jcq.entity.Member;

import java.util.Date;
import java.util.List;

/**
 * Created by Sobte on 2018/7/15.<br>
 * Time: 2018/7/15 14:43<br>
 * Email: i@sobte.me<br>
 * 群成员消息
 *
 * @author Sobte
 */
public class GroupMsg extends Sender {

    /**
     * 群成员信息
     */
    protected final Member member;

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param msg     消息
     * @param msgId   消息ID
     * @param groupId 群号
     * @param qqId    QQ号
     */
    public GroupMsg(String msg, int msgId, long groupId, long qqId) {
        this(msg, msgId, groupId, qqId, true);
    }

    /**
     * 有参构造函数
     *
     * @param msg     消息
     * @param msgId   消息ID
     * @param groupId 群号
     * @param qqId    QQ号
     * @param load    是否初始加载信息
     */
    public GroupMsg(String msg, int msgId, long groupId, long qqId, boolean load) {
        this(EventType.GroupMsg, msg, msgId, groupId, qqId, load);
    }

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param type    来源事件类型
     * @param msg     消息
     * @param msgId   消息ID
     * @param groupId 群号
     * @param qqId    QQ号
     */
    public GroupMsg(EventType type, String msg, int msgId, long groupId, long qqId) {
        this(type, msg, msgId, groupId, qqId, true);
    }

    /**
     * 有参构造函数
     *
     * @param type    来源事件类型
     * @param msg     消息
     * @param msgId   消息ID
     * @param groupId 群号
     * @param qqId    QQ号
     * @param load    是否初始加载信息
     */
    public GroupMsg(EventType type, String msg, int msgId, long groupId, long qqId, boolean load) {
        this(type, msg, msgId, new Member(groupId, qqId));
        if (load) refresh();
    }

    /**
     * 有参构造函数
     *
     * @param msg  酷Q消息
     * @param load 是否初始加载信息
     */
    public GroupMsg(CQMsg msg, boolean load) {
        this(msg, new Member(msg.getFromId(), msg.getFromQQ()));
        if (load) refresh();
    }

    /**
     * 有参构造函数 默认自动加载信息
     *
     * @param msg 酷Q消息
     */
    public GroupMsg(CQMsg msg) {
        this(msg, true);
    }

    /**
     * 有参构造函数
     *
     * @param type   来源事件类型
     * @param msg    消息
     * @param msgId  消息ID
     * @param member 初始成员信息
     */
    GroupMsg(EventType type, String msg, int msgId, Member member) {
        super(type, msg, msgId, member);
        this.member = member;
    }

    /**
     * 有参构造函数
     *
     * @param msg    酷Q消息
     * @param member 初始成员信息
     */
    GroupMsg(CQMsg msg, Member member) {
        super(msg, member);
        this.member = member;
    }

    /**
     * 获取群号
     *
     * @return 群号
     */
    public long getGroupId() {
        return member.getGroupId();
    }

    /**
     * 设置名片
     *
     * @param card 新名片(昵称)
     * @return 本发送者对象
     */
    public GroupMsg setCard(String card) {
        status = CQ.setGroupCard(getGroupId(), getQQId(), card);
        if (status == 0) member.setCard(card);
        return this;
    }

    /**
     * 获取名片
     *
     * @return 名片
     */
    public String getCard() {
        return member.getCard();
    }

    /**
     * 获取地区
     *
     * @return 地区
     */
    public String getArea() {
        return member.getArea();
    }

    /**
     * 获取加群时间
     *
     * @return 加群时间
     */
    public Date getAddTime() {
        return member.getAddTime();
    }

    /**
     * 获取最后发言时间
     *
     * @return 最后发言时间
     */
    public Date getLastTime() {
        return member.getLastTime();
    }

    /**
     * 获取等级名称
     *
     * @return 等级名称
     */
    public String getLevelName() {
        return member.getLevelName();
    }

    /**
     * 获取管理权限
     *
     * @return 管理权限
     */
    public Authority getAuthority() {
        return Authority.valueOf(member.getAuthority());
    }

    /**
     * 设置专属头衔,需群主权限
     *
     * @param title      头衔，如果要删除，这里填空
     * @param expireTime 专属头衔有效期，单位为秒。如果永久有效，这里填写-1
     * @return 本发送者对象
     */
    public GroupMsg setTitle(String title, long expireTime) {
        status = CQ.setGroupSpecialTitle(getGroupId(), getQQId(), title, expireTime);
        if (status == 0) {
            member.setTitle(title);
            member.setTitleExpire(expireTime != -1 ? new Date(System.currentTimeMillis() + expireTime) : null);
        }
        return this;
    }

    /**
     * 设置专属头衔,需群主权限(永久)<br>
     * 如需设置时间 请调用 {@link #setTitle(String, long) setTitle(String, long)}
     *
     * @param title 头衔，如果要删除，这里填空
     * @return 本发送者对象
     * @see #setTitle(String, long) 设置专属头衔
     */
    public GroupMsg setTitle(String title) {
        return setTitle(title, -1);
    }

    /**
     * 获取专属头衔
     *
     * @return 专属头衔
     */
    public String getTitle() {
        return member.getTitle();
    }

    /**
     * 获取专属头衔过期时间 null 代表不过期
     *
     * @return 过期时间
     */
    public Date getTitleExpire() {
        return member.getTitleExpire();
    }

    /**
     * 是否是不良记录成员
     *
     * @return 是否是不良记录成员
     */
    public boolean isBad() {
        return member.isBad();
    }

    /**
     * 是否允许修改名片
     *
     * @return 是否允许修改名片
     */
    public boolean isModifyCard() {
        return member.isModifyCard();
    }

    /**
     * 获取群成员列表
     *
     * @return 如果成功，返回群成员列表
     */
    public List<Member> getGroupMemberList() {
        return CQ.getGroupMemberList(getGroupId());
    }

    /**
     * 撤回消息
     *
     * @param msgId 消息ID
     * @return 本发送者对象
     */
    @Override
    public GroupMsg deleteMsg(long msgId) {
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
    public GroupMsg deleteMsg() {
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
    public GroupMsg sendLike(int times) {
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
    public GroupMsg sendLike() {
        super.sendLike();
        return this;
    }

    /**
     * 向当前群组,发送群聊消息,不立即发送,创建消息处理对象
     *
     * @return 消息处理对象
     */
    public MsgBuilder sendGroupMsg() {
        return CQ.sendGroupMsg(getGroupId());
    }

    /**
     * 向当前群组,发送群聊消息
     *
     * @param msg 消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    public int sendGroupMsg(String msg) {
        return status = CQ.sendGroupMsg(getGroupId(), msg);
    }

    /**
     * 移除此群员
     *
     * @param notBack 如果为true，则“不再接收此人加群申请”，请慎用
     * @return 本发送者对象
     */
    public GroupMsg kick(boolean notBack) {
        status = CQ.setGroupKick(getGroupId(), getQQId(), notBack);
        return this;
    }

    /**
     * 移除此群员
     *
     * @return 本发送者对象
     */
    public GroupMsg kick() {
        return kick(false);
    }

    /**
     * 禁言此群员
     *
     * @param banTime 禁言的时间，单位为秒。如果要解禁 调用 {@link #let() let}，或者这里填写0
     * @return 本发送者对象
     * @see #let() 解禁
     */
    public GroupMsg ban(long banTime) {
        status = CQ.setGroupBan(getGroupId(), getQQId(), banTime);
        return this;
    }

    /**
     * 解禁此群员
     *
     * @return 本发送者对象
     * @see #ban(long) 禁言
     */
    public GroupMsg let() {
        return ban(0);
    }

    /**
     * 取消此群员管理员权限
     *
     * @return 本发送者对象
     */
    public GroupMsg adminBan() {
        status = CQ.setGroupAdmin(getGroupId(), getQQId(), false);
        return this;
    }

    /**
     * 给予此群员管理员权限
     *
     * @return 本发送者对象
     */
    public GroupMsg adminLet() {
        status = CQ.setGroupAdmin(getGroupId(), getQQId(), true);
        return this;
    }

    /**
     * 关闭(禁止)群匿名
     *
     * @return 本发送者对象
     */
    public GroupMsg anonymousBan() {
        status = CQ.setGroupAnonymous(getGroupId(), false);
        return this;
    }

    /**
     * 开启(允许)群匿名
     *
     * @return 本发送者对象
     */
    public GroupMsg anonymousLet() {
        status = CQ.setGroupAnonymous(getGroupId(), true);
        return this;
    }

    /**
     * 全群禁言
     *
     * @return 本发送者对象
     */
    public GroupMsg wholeBan() {
        status = CQ.setGroupWholeBan(getGroupId(), true);
        return this;
    }

    /**
     * 全体解禁
     *
     * @return 本发送者对象
     */
    public GroupMsg wholeLet() {
        status = CQ.setGroupWholeBan(getGroupId(), false);
        return this;
    }

    /**
     * 退出此群,慎用,此接口需要严格授权<br>
     * 指当前登入QQ退出此群，移除群员请调用 {@link #kick() kick}
     *
     * @return 本发送者对象
     */
    public GroupMsg leave() {
        status = CQ.setGroupLeave(getGroupId(), getAuthority() == Authority.Owner);
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
    protected boolean refreshInfo(long groupId, long qqId, boolean notCache) {
        return CQ.getGroupMemberInfo(member, groupId, qqId, notCache) != null;
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
        return refreshInfo(getGroupId(), qqId, notCache);
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param groupId  群号
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 本发送者对象
     */
    public GroupMsg refresh(long groupId, long qqId, boolean notCache) {
        refresh = refreshInfo(groupId, qqId, notCache);
        return this;
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param groupId 群号
     * @param qqId    QQ号
     * @return 本发送者对象
     */
    public GroupMsg refresh(long groupId, long qqId) {
        return refresh(groupId, qqId, false);
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 本发送者对象
     */
    @Override
    public GroupMsg refresh(long qqId, boolean notCache) {
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
    public GroupMsg refresh(long qqId) {
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
    public GroupMsg refresh(boolean notCache) {
        super.refresh(notCache);
        return this;
    }

    /**
     * 刷新本对象成员信息
     *
     * @return 本发送者对象
     */
    @Override
    public GroupMsg refresh() {
        super.refresh();
        return this;
    }
}
