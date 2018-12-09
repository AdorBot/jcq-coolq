package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.EventType;
import com.sobte.cqp.jcq.annotation.Gender;
import com.sobte.cqp.jcq.entity.CQDebug;
import com.sobte.cqp.jcq.entity.CQStatus;
import com.sobte.cqp.jcq.entity.CoolQ;

/**
 * Created by Sobte on 2018/7/13.<br>
 * Time: 2018/7/13 13:45<br>
 * Email: i@sobte.me<br>
 *
 * @author Sobte
 */
abstract class AbstractSender implements Message {

    /**
     * 酷Q操作变量
     */
    protected CoolQ CQ = CQDebug.getInstance();

    /**
     * 来源事件类型
     */
    protected EventType type;

    /**
     * 消息ID
     */
    protected int msgId;

    /**
     * 刷新状态
     */
    protected boolean refresh;

    /**
     * 消息
     */
    protected String msg;

    /**
     * 状态码
     */
    protected int status;

    /**
     * 有参构造函数
     *
     * @param type  来源事件类型
     * @param msg   消息
     * @param msgId 消息ID
     */
    public AbstractSender(EventType type, String msg, int msgId) {
        this.type = type;
        this.msgId = msgId;
        this.msg = msg;
    }

    /**
     * 有参构造函数
     *
     * @param msg 酷Q消息
     */
    public AbstractSender(CQMsg msg) {
        this.type = msg.getType();
        this.msgId = msg.getMsgId();
        this.msg = msg.getMsg();
    }

    /**
     * 设置酷Q操作变量
     *
     * @param CQ 酷Q操作变量
     */
    public void setCoolQ(CoolQ CQ) {
        this.CQ = CQ;
    }

    /**
     * 获取来源事件类型
     *
     * @return 事件类型
     */
    public EventType getType() {
        return type;
    }

    /**
     * 获取消息ID
     *
     * @return 消息ID
     */
    @Override
    public int getMsgId() {
        return msgId;
    }

    /**
     * 获取消息
     *
     * @return 消息
     */
    @Override
    public String getMsg() {
        return msg;
    }

    /**
     * 获取QQ号
     *
     * @return QQ号
     */
    public abstract long getQQId();

    /**
     * 获取性别
     *
     * @return 性别
     */
    public abstract Gender getGender();

    /**
     * 获取年龄
     *
     * @return 年龄
     */
    public abstract int getAge();

    /**
     * 获取昵称
     *
     * @return 昵称
     */
    public abstract String getNick();

    /**
     * 获取解析消息,CQ码和普通消息
     *
     * @return 解析后消息, CQ码和普通消息
     */
    @Override
    public CoolQMsg getCoolQMsg() {
        return new CoolQMsg(msg);
    }

    /**
     * 获取解析消息,CQ码(不包含普通消息)
     *
     * @return 解析后消息, CQ码(不包含普通消息)
     */
    @Override
    public CoolQCode getCoolQCode() {
        return new CoolQCode(msg);
    }

    /**
     * 向当前发送者,发送私聊消息,不立即发送,创建消息处理对象
     *
     * @return 消息处理对象
     */
    public MsgBuilder sendPrivateMsg() {
        return CQ.sendPrivateMsg(getQQId());
    }

    /**
     * 向当前发送者,发送私聊消息
     *
     * @param msg 消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    public int sendPrivateMsg(String msg) {
        return status = CQ.sendPrivateMsg(getQQId(), msg);
    }

    /**
     * 撤回消息
     *
     * @param msgId 消息ID
     * @return 本发送者对象
     */
    public AbstractSender deleteMsg(long msgId) {
        status = CQ.deleteMsg(msgId);
        return this;
    }

    /**
     * 撤回消息,当前发送者发送的消息
     *
     * @return 本发送者对象
     * @see #deleteMsg(long) 撤回指定消息
     */
    public AbstractSender deleteMsg() {
        return deleteMsg(msgId);
    }

    /**
     * 向当前发送者,发送手机赞,多次
     *
     * @param times 赞的次数,最多10次
     * @return 本发送者对象
     */
    public AbstractSender sendLike(int times) {
        status = CQ.sendLike(getQQId(), times);
        return this;
    }

    /**
     * 向当前发送者,发送手机赞,赞一次
     *
     * @return 本发送者对象
     * @see #sendLike(int) 发送多次手机赞
     */
    public AbstractSender sendLike() {
        return sendLike(1);
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 是否刷新成功
     */
    protected abstract boolean refreshInfo(long qqId, boolean notCache);

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param qqId     QQ号
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 本发送者对象
     */
    public AbstractSender refresh(long qqId, boolean notCache) {
        refresh = refreshInfo(qqId, notCache);
        return this;
    }

    /**
     * 根据指定信息刷新本对象成员信息
     *
     * @param qqId QQ号
     * @return 本发送者对象
     */
    public AbstractSender refresh(long qqId) {
        return refresh(qqId, false);
    }

    /**
     * 刷新本对象成员信息
     *
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 本发送者对象
     */
    public AbstractSender refresh(boolean notCache) {
        return refresh(getQQId(), notCache);
    }

    /**
     * 刷新本对象成员信息
     *
     * @return 本发送者对象
     */
    public AbstractSender refresh() {
        return refresh(false);
    }

    /**
     * 获取最后一次刷新的状态
     *
     * @return 是否刷新成功
     */
    public boolean getLastRefreshStatus() {
        return refresh;
    }

    /**
     * 接收消息中的语音(record)
     *
     * @param outformat 应用所需的语音文件格式，目前支持 mp3,amr,wma,m4a,spx,ogg,wav,flac
     * @return 返回保存在 \data\record\ 目录下的文件名
     */
    public String getRecord(String outformat) {
        return CQ.getRecord(new CoolQCode(msg).getRecord(), outformat);
    }

    /**
     * 获取最后状态
     *
     * @return 描述信息
     */
    public CQStatus getLastStatus() {
        return CQStatus.getStatus(status < 0 ? status : 0);
    }

    /**
     * 当前消息对象转成文本
     *
     * @return 消息文本
     */
    @Override
    public String toString() {
        return "{" + getQQId() + ":\"" + msg + "\"}";
    }
}
