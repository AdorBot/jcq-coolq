package com.sobte.cqp.jcq.annotation;

import com.sobte.cqp.jcq.entity.IAuth;

/**
 * Created by Sobte on 2018/6/26.<br>
 * Time: 2018/6/26 23:27<br>
 * Email: i@sobte.me<br>
 *
 * @author Sobte
 */
public enum AuthType implements IAuth {

    /**
     * [敏感]取Cookies,对应CoolQ的方法 getCookies / getCsrfToken
     */
    GetCookies(AUTH_GetCookies),

    /**
     * 接收语音,对应CoolQ的方法 getRecord
     */
    GetRecord(AUTH_GetRecord),

    /**
     * 发送群消息,对应CoolQ的方法 sendGroupMsg
     */
    SendGroupMsg(AUTH_SendGroupMsg),
    /**
     * 发送讨论组消息,对应CoolQ的方法 sendDiscussMsg
     */
    SendDiscussMsg(AUTH_SendDiscussMsg),
    /**
     * 发送私聊消息,对应CoolQ方法 sendPrivateMsg
     */
    SendPrivateMsg(AUTH_SendPrivateMsg),
    /**
     * 发送赞,对应CoolQ方法 sendLike
     */
    SendLike(AUTH_SendLike),
    /**
     * 置群员移除,对应CoolQ方法 setGroupKick
     */
    SetGroupKick(AUTH_SetGroupKick),
    /**
     * 置群员禁言,对应CoolQ方法 setGroupBan
     */
    SetGroupBan(AUTH_SetGroupBan),
    /**
     * 置群管理员,对应CoolQ方法 setGroupAdmin
     */
    SetGroupAdmin(AUTH_SetGroupAdmin),
    /**
     * 置全群禁言,对应CoolQ方法 setGroupWholeBan
     */
    SetGroupWholeBan(AUTH_SetGroupWholeBan),
    /**
     * 置匿名群员禁言,对应CoolQ方法 setGroupAnonymousBan
     */
    SetGroupAnonymousBan(AUTH_SetGroupAnonymousBan),
    /**
     * 置群匿名设置,对应CoolQ方法 setGroupAnonymous
     */
    SetGroupAnonymous(AUTH_SetGroupAnonymous),
    /**
     * 置群成员名片,对应CoolQ方法 setGroupCard
     */
    SetGroupCard(AUTH_SetGroupCard),
    /**
     * [敏感]置群退出,对应CoolQ方法 setGroupLeave
     */
    SetGroupLeave(AUTH_SetGroupLeave),
    /**
     * 置群成员专属头衔,对应CoolQ方法 setGroupSpecialTitle
     */
    SetGroupSpecialTitle(AUTH_SetGroupSpecialTitle),
    /**
     * 取群成员信息,对应CoolQ方法 getGroupMemberInfoV2 / getGroupMemberInfo
     */
    GetGroupMemberInfo(AUTH_GetGroupMemberInfo),
    /**
     * 取陌生人信息,对应CoolQ方法 getStrangerInfo
     */
    GetStrangerInfo(AUTH_GetStrangerInfo),
    /**
     * 置讨论组退出,对应CoolQ方法 setDiscussLeave
     */
    SetDiscussLeave(AUTH_SetDiscussLeave),
    /**
     * 置好友添加请求,对应CoolQ方法 setFriendAddRequest
     */
    SetFriendAddRequest(AUTH_SetFriendAddRequest),
    /**
     * 置群添加请求,对应CoolQ方法 setGroupAddRequest
     */
    SetGroupAddRequest(AUTH_SetGroupAddRequest),
    /**
     * 取群成员列表,对应CoolQ方法 getGroupMemberList
     */
    GetGroupMemberList(AUTH_GetGroupMemberList),
    /**
     * 取群列表,对应CoolQ方法 getGroupList
     */
    GetGroupList(AUTH_GetGroupList),
    /**
     * 撤回消息,对应CoolQ方法 deleteMsg
     */
    DeleteMsg(AUTH_DeleteMsg);

    /**
     * 权限类型值
     */
    private int value;

    AuthType(int value) {
        this.value = value;
    }

    /**
     * 获取权限类型
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
    public static AuthType valueOf(int value) {
        switch (value) {
            case AUTH_GetCookies:
                return GetCookies;
            case AUTH_GetRecord:
                return GetRecord;
            case AUTH_SendGroupMsg:
                return SendGroupMsg;
            case AUTH_SendDiscussMsg:
                return SendDiscussMsg;
            case AUTH_SendPrivateMsg:
                return SendPrivateMsg;
            case AUTH_SendLike:
                return SendLike;
            case AUTH_SetGroupKick:
                return SetGroupKick;
            case AUTH_SetGroupBan:
                return SetGroupBan;
            case AUTH_SetGroupAdmin:
                return SetGroupAdmin;
            case AUTH_SetGroupWholeBan:
                return SetGroupWholeBan;
            case AUTH_SetGroupAnonymousBan:
                return SetGroupAnonymousBan;
            case AUTH_SetGroupAnonymous:
                return SetGroupAnonymous;
            case AUTH_SetGroupCard:
                return SetGroupCard;
            case AUTH_SetGroupLeave:
                return SetGroupLeave;
            case AUTH_SetGroupSpecialTitle:
                return SetGroupSpecialTitle;
            case AUTH_GetGroupMemberInfo:
                return GetGroupMemberInfo;
            case AUTH_GetStrangerInfo:
                return GetStrangerInfo;
            case AUTH_SetDiscussLeave:
                return SetDiscussLeave;
            case AUTH_SetFriendAddRequest:
                return SetFriendAddRequest;
            case AUTH_SetGroupAddRequest:
                return SetGroupAddRequest;
            case AUTH_GetGroupMemberList:
                return GetGroupMemberList;
            case AUTH_GetGroupList:
                return GetGroupList;
            case AUTH_DeleteMsg:
                return DeleteMsg;
            default:
                return null;
        }
    }

}
