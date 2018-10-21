package com.sobte.cqp.jcq.event;

/**
 * Created by Sobte on 2018/4/20.<br>
 * Time: 2018/4/20 22:45<br>
 * Email: i@sobte.me<br>
 * 酷Q接收器，基本事件
 *
 * @author Sobte
 */
public interface JcqListener {

    /**
     * 酷Q启动 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_Startup 1001})<br>
     * 本方法会在酷Q【主线程】中被调用。<br>
     * 请在这里执行插件初始化代码。<br>
     * 请务必尽快返回本子程序，否则会卡住其他插件以及主程序的加载。
     *
     * @return 请固定返回0
     */
    int startup();

    /**
     * 酷Q退出 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_Exit 1002})<br>
     * 本方法会在酷Q【主线程】中被调用。<br>
     * 无论本应用是否被启用，本函数都会在酷Q退出前执行一次，请在这里执行插件关闭代码。
     *
     * @return 请固定返回0，返回后酷Q将很快关闭，请不要再通过线程等方式执行其他代码。
     */
    int exit();

    /**
     * 应用已被启用 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_Enable 1003})<br>
     * 当应用被启用后，将收到此事件。<br>
     * 如果酷Q载入时应用已被启用，则在 {@link #startup startup}(Type=1001,酷Q启动) 被调用后，本函数也将被调用一次。<br>
     * 如非必要，不建议在这里加载窗口。
     *
     * @return 请固定返回0。
     */
    int enable();

    /**
     * 应用将被停用 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_Disable 1004})<br>
     * 当应用被停用前，将收到此事件。<br>
     * 如果酷Q载入时应用已被停用，则本函数【不会】被调用。<br>
     * 无论本应用是否被启用，酷Q关闭前本函数都【不会】被调用。
     *
     * @return 请固定返回0。
     */
    int disable();

    /**
     * 私聊消息 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_PrivateMsg 21})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType 子类型，11/来自好友 1/来自在线状态 2/来自群 3/来自讨论组
     * @param msgId   消息ID
     * @param fromQQ  来源QQ
     * @param msg     消息内容
     * @param font    字体
     * @return 返回值*不能*直接返回文本 如果要回复消息，请调用api发送<br>
     * 这里 返回  {@link com.sobte.cqp.jcq.entity.IMsg#MSG_INTERCEPT} - 截断本条消息，不再继续处理<br>
     * 注意：应用优先级设置为"最高"(10000)时，不得使用本返回值<br>
     * 如果不回复消息，交由之后的应用/过滤器处理，这里 返回  {@link com.sobte.cqp.jcq.entity.IMsg#MSG_IGNORE MSG_IGNORE} - 忽略本条消息
     */
    int privateMsg(int subType, int msgId, long fromQQ, String msg, int font);

    /**
     * 群消息 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_GroupMsg 2})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType       子类型，目前固定为1
     * @param msgId         消息ID
     * @param fromGroup     来源群号
     * @param fromQQ        来源QQ号
     * @param fromAnonymous 来源匿名者
     * @param msg           消息内容
     * @param font          字体
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg, int font);

    /**
     * 讨论组消息 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_DiscussMsg 4})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType     子类型，目前固定为1
     * @param msgId       消息ID
     * @param fromDiscuss 来源讨论组
     * @param fromQQ      来源QQ号
     * @param msg         消息内容
     * @param font        字体
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int discussMsg(int subType, int msgId, long fromDiscuss, long fromQQ, String msg, int font);

    /**
     * 群文件上传事件 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_GroupUpload 11})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType   子类型，目前固定为1
     * @param sendTime  发送时间(时间戳)// 10位时间戳
     * @param fromGroup 来源群号
     * @param fromQQ    来源QQ号
     * @param file      上传文件信息
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int groupUpload(int subType, int sendTime, long fromGroup, long fromQQ, String file);

    /**
     * 群事件-管理员变动 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_System_GroupAdmin 101})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType        子类型，1/被取消管理员 2/被设置管理员
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param beingOperateQQ 被操作QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int groupAdmin(int subType, int sendTime, long fromGroup, long beingOperateQQ);

    /**
     * 群事件-群成员减少 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_System_GroupMemberDecrease 102})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType        子类型，1/群员离开 2/群员被踢
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param fromQQ         操作者QQ(仅子类型为2时存在)
     * @param beingOperateQQ 被操作QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int groupMemberDecrease(int subType, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ);

    /**
     * 群事件-群成员增加 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_System_GroupMemberIncrease 103})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType        子类型，1/管理员已同意 2/管理员邀请
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param fromQQ         操作者QQ(即管理员QQ)
     * @param beingOperateQQ 被操作QQ(即加群的QQ)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int groupMemberIncrease(int subType, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ);

    /**
     * 好友事件-好友已添加 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_Friend_Add 201})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType  子类型，目前固定为1
     * @param sendTime 发送时间(时间戳)
     * @param fromQQ   来源QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int friendAdd(int subType, int sendTime, long fromQQ);

    /**
     * 请求-好友添加 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_Request_AddFriend 301})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType      子类型，目前固定为1
     * @param sendTime     发送时间(时间戳)
     * @param fromQQ       来源QQ
     * @param msg          附言
     * @param responseFlag 反馈标识(处理请求用)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int requestAddFriend(int subType, int sendTime, long fromQQ, String msg, String responseFlag);

    /**
     * 请求-群添加 (Type={@link com.sobte.cqp.jcq.event.IType#EVENT_Request_AddGroup 302})<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType      子类型，1/他人申请入群 2/自己(即登录号)受邀入群
     * @param sendTime     发送时间(时间戳)
     * @param fromGroup    来源群号
     * @param fromQQ       来源QQ
     * @param msg          附言
     * @param responseFlag 反馈标识(处理请求用)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    int requestAddGroup(int subType, int sendTime, long fromGroup, long fromQQ, String msg, String responseFlag);

}
