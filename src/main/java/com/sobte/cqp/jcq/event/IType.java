package com.sobte.cqp.jcq.event;

/**
 * Created by Sobte on 2018/3/11.
 * Time: 0:16
 * Email: i@sobte.me
 * 消息类型
 */
public interface IType {

    /**
     * 私聊消息处理
     */
    int EVENT_PrivateMsg = 21;
    /**
     * 群消息处理
     */
    int EVENT_GroupMsg = 2;
    /**
     * 讨论组消息处理
     */
    int EVENT_DiscussMsg = 4;
    /**
     * 群文件上传事件处理
     */
    int EVENT_GroupUpload = 11;
    /**
     * 群管理变动事件处理
     */
    int EVENT_System_GroupAdmin = 101;
    /**
     * 群成员减少事件处理
     */
    int EVENT_System_GroupMemberDecrease = 102;
    /**
     * 群成员增加事件处理
     */
    int EVENT_System_GroupMemberIncrease = 103;
    /**
     * 好友已添加事件处理
     */
    int EVENT_Friend_Add = 201;
    /**
     * 好友添加请求处理
     */
    int EVENT_Request_AddFriend = 301;
    /**
     * 群添加请求处理
     */
    int EVENT_Request_AddGroup = 302;
    /**
     * 酷Q启动事件
     */
    int EVENT_Startup = 1001;
    /**
     * 酷Q关闭事件
     */
    int EVENT_Exit = 1002;
    /**
     * 应用已被启用
     */
    int EVENT_Enable = 1003;
    /**
     * 应用将被停用
     */
    int EVENT_Disable = 1004;

}
