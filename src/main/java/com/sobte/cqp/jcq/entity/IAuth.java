package com.sobte.cqp.jcq.entity;

/**
 * Created by Sobte on 2018/4/19.<br>
 * Time: 2018/4/19 21:19<br>
 * Email: i@sobte.me<br>
 * 接口权限
 *
 * @author Sobte
 */
public interface IAuth {

    /**
     * [敏感]取Cookies,对应CoolQ的方法 getCookies / getCsrfToken
     */
    int AUTH_GetCookies = 20;

    /**
     * 接收语音,对应CoolQ的方法 getRecord
     */
    int AUTH_GetRecord = 30;

    /**
     * 发送群消息,对应CoolQ的方法 sendGroupMsg
     */
    int AUTH_SendGroupMsg = 101;

    /**
     * 发送讨论组消息,对应CoolQ的方法 sendDiscussMsg
     */
    int AUTH_SendDiscussMsg = 103;

    /**
     * 发送私聊消息,对应CoolQ方法 sendPrivateMsg
     */
    int AUTH_SendPrivateMsg = 106;

    /**
     * 发送赞,对应CoolQ方法 sendLike
     */
    int AUTH_SendLike = 110;

    /**
     * 置群员移除,对应CoolQ方法 setGroupKick
     */
    int AUTH_SetGroupKick = 120;

    /**
     * 置群员禁言,对应CoolQ方法 setGroupBan
     */
    int AUTH_SetGroupBan = 121;

    /**
     * 置群管理员,对应CoolQ方法 setGroupAdmin
     */
    int AUTH_SetGroupAdmin = 122;

    /**
     * 置全群禁言,对应CoolQ方法 setGroupWholeBan
     */
    int AUTH_SetGroupWholeBan = 123;

    /**
     * 置匿名群员禁言,对应CoolQ方法 setGroupAnonymousBan
     */
    int AUTH_SetGroupAnonymousBan = 124;

    /**
     * 置群匿名设置,对应CoolQ方法 setGroupAnonymous
     */
    int AUTH_SetGroupAnonymous = 125;

    /**
     * 置群成员名片,对应CoolQ方法 setGroupCard
     */
    int AUTH_SetGroupCard = 126;

    /**
     * [敏感]置群退出,对应CoolQ方法 setGroupLeave
     */
    int AUTH_SetGroupLeave = 127;

    /**
     * 置群成员专属头衔,对应CoolQ方法 setGroupSpecialTitle
     */
    int AUTH_SetGroupSpecialTitle = 128;

    /**
     * 取群成员信息,对应CoolQ方法 getGroupMemberInfoV2 / getGroupMemberInfo
     */
    int AUTH_GetGroupMemberInfo = 130;

    /**
     * 取陌生人信息,对应CoolQ方法 getStrangerInfo
     */
    int AUTH_GetStrangerInfo = 131;

    /**
     * 置讨论组退出,对应CoolQ方法 setDiscussLeave
     */
    int AUTH_SetDiscussLeave = 140;

    /**
     * 置好友添加请求,对应CoolQ方法 setFriendAddRequest
     */
    int AUTH_SetFriendAddRequest = 150;

    /**
     * 置群添加请求,对应CoolQ方法 setGroupAddRequest
     */
    int AUTH_SetGroupAddRequest = 151;

    /**
     * 取群成员列表,对应CoolQ方法 getGroupMemberList
     */
    int AUTH_GetGroupMemberList = 160;

    /**
     * 取群列表,对应CoolQ方法 getGroupList
     */
    int AUTH_GetGroupList = 161;

    /**
     * 撤回消息,对应CoolQ方法 deleteMsg
     */
    int AUTH_DeleteMsg = 180;

}
