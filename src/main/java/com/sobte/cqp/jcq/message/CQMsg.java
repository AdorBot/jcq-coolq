package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.annotation.EventType;
import com.sobte.cqp.jcq.entity.ISystem;

import java.util.Date;

/**
 * Created by Sobte on 2018/6/14.<br>
 * Time: 2018/6/14 20:58<br>
 * Email: i@sobte.me<br>
 * 酷Q消息
 *
 * @author Sobte
 */
public class CQMsg implements Message, java.io.Serializable {

    private static final long serialVersionUID = -3115410970443193412L;

    private EventType type;
    private int subType;
    private Date sendTime;
    private int msgId;
    private long fromId;
    private long fromQQ;
    private String msg;
    private String source;

    public CQMsg(EventType type, int subType, Date sendTime, int msgId, long fromId, long fromQQ, String msg) {
        this.type = type;
        this.subType = subType;
        this.sendTime = sendTime;
        this.msgId = msgId;
        this.fromId = fromId;
        this.fromQQ = fromQQ;
        this.msg = msg;
    }

    public CQMsg(EventType type, int subType, Date sendTime, int msgId, long fromId, long fromQQ, String msg, String source) {
        this(type, subType, sendTime, msgId, fromId, fromQQ, msg);
        this.source = source;
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
     * 获取消息来源群组ID 非群组 默认0
     *
     * @return 群组ID
     */
    public long getFromId() {
        return fromId;
    }

    /**
     * 获取消息来源QQ
     *
     * @return QQ号
     */
    public long getFromQQ() {
        return fromQQ;
    }

    /**
     * 获取发送时间
     *
     * @return 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 获取子类型 默认为0
     *
     * @return 获取子类型
     */
    public int getSubType() {
        return subType;
    }

    /**
     * 获取事件类型
     *
     * @return 事件类型
     */
    public EventType getType() {
        return type;
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
     * 转成发送者对象
     *
     * @return 发送者
     */
    public Sender toSender() {
        switch (type) {
            case GroupMsg:
                return fromQQ == ISystem.System_QQID_Anonymous ? new AnonymMsg(this, source) : new GroupMsg(this);
            case PrivateMsg:
                return new PrivateMsg(this);
            case DiscussMsg:
                return new DiscussMsg(this);
        }
        return null;
    }

}
