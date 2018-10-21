package com.sobte.cqp.jcq.annotation;

import com.sobte.cqp.jcq.event.IType;

/**
 * Created by Sobte on 2018/6/19.<br>
 * Time: 2018/6/19 21:58<br>
 * Email: i@sobte.me<br>
 * 事件类型
 *
 * @author Sobte
 */
public enum EventType implements IType {

    /**
     * 酷Q启动事件
     */
    Startup(EVENT_Startup),
    /**
     * 酷Q关闭事件
     */
    Exit(EVENT_Exit),
    /**
     * 应用已被启用
     */
    Enable(EVENT_Enable),
    /**
     * 应用将被停用
     */
    Disable(EVENT_Disable),
    /**
     * 私聊消息处理
     */
    PrivateMsg(EVENT_PrivateMsg),
    /**
     * 群消息处理
     */
    GroupMsg(EVENT_GroupMsg),
    /**
     * 讨论组消息处理
     */
    DiscussMsg(EVENT_DiscussMsg),
    /**
     * 群文件上传事件处理
     */
    GroupUpload(EVENT_GroupUpload),
    /**
     * 群管理变动事件处理
     */
    GroupAdmin(EVENT_System_GroupAdmin),
    /**
     * 群成员减少事件处理
     */
    GroupMemberDecrease(EVENT_System_GroupMemberDecrease),
    /**
     * 群成员增加事件处理
     */
    GroupMemberIncrease(EVENT_System_GroupMemberIncrease),
    /**
     * 好友已添加事件处理
     */
    FriendAdd(EVENT_Friend_Add),
    /**
     * 好友添加请求处理
     */
    RequestAddFriend(EVENT_Request_AddFriend),
    /**
     * 群添加请求处理
     */
    RequestAddGroup(EVENT_Request_AddGroup);

    /**
     * 事件类型值
     */
    private int value;

    EventType(int value) {
        this.value = value;
    }

    /**
     * 获取事件类型
     *
     * @return 值
     */
    public int value() {
        return value;
    }

    /**
     * 可以将值转换为枚举
     *
     * @param value 值
     * @return 枚举
     */
    public static EventType valueOf(int value) {
        switch (value) {
            case EVENT_Startup:
                return Startup;
            case EVENT_Exit:
                return Exit;
            case EVENT_Enable:
                return Enable;
            case EVENT_Disable:
                return Disable;
            case EVENT_PrivateMsg:
                return PrivateMsg;
            case EVENT_GroupMsg:
                return GroupMsg;
            case EVENT_DiscussMsg:
                return DiscussMsg;
            case EVENT_GroupUpload:
                return GroupUpload;
            case EVENT_System_GroupAdmin:
                return GroupAdmin;
            case EVENT_System_GroupMemberDecrease:
                return GroupMemberDecrease;
            case EVENT_System_GroupMemberIncrease:
                return GroupMemberIncrease;
            case EVENT_Friend_Add:
                return FriendAdd;
            case EVENT_Request_AddFriend:
                return RequestAddFriend;
            case EVENT_Request_AddGroup:
                return RequestAddGroup;
            default:
                return null;
        }
    }

}
