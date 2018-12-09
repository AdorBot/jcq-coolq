package com.sobte.cqp.jcq.entity;

import com.sobte.cqp.jcq.annotation.AuthType;
import com.sobte.cqp.jcq.message.MsgBuilder;
import com.sobte.cqp.jcq.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sobte on 2018/1/29.
 * Time: 15:32
 * Email: i@sobte.me
 * CoolQ 操作的核心类
 *
 * @author Sobte
 * @version 1.1.0
 * @since 1.7
 */
public abstract class CoolQ implements ILog, IRequest, IMsg, ICQVer {

    /**
     * AC码
     */
    private int authCode;
    /**
     * 应用目录
     */
    protected String appDirectory;
    /**
     * 应用名称
     */
    protected String appName;

    /**
     * 状态码
     */
    protected int status;

    /**
     * 初始化
     *
     * @param authCode AC码
     */
    protected CoolQ(int authCode) {
        this.authCode = authCode;
    }

    /**
     * 初始化
     *
     * @param authCode     AC码
     * @param appDirectory 应用目录
     * @param appName      应用名称
     */
    protected CoolQ(int authCode, String appDirectory, String appName) {
        this.authCode = authCode;
        this.appDirectory = appDirectory;
        this.appName = appName;
    }

    private native int setFatal(int authCode, String errorInfo);

    /**
     * 置错误提示
     *
     * @param errorInfo 错误信息
     * @return 状态码
     */
    public int setFatal(String errorInfo) {
        return status = setFatal(authCode, errorInfo);
    }

    private native String getAppDirectory(int authCode);

    /**
     * 获取应用目录
     *
     * @return 应用目录, 返回的路径末尾带"\"
     */
    public String getAppDirectory() {
        if (StringUtils.isEmpty(appDirectory)) {
            appDirectory = getAppDirectory(authCode);
        }
        File file = new File(appDirectory);
        if (!file.isDirectory())
            file.mkdirs();
        return appDirectory;
    }

    private native long getLoginQQ(int authCode);

    /**
     * 获取登录QQ
     *
     * @return 登录QQ
     */
    public long getLoginQQ() {
        return getLoginQQ(authCode);
    }

    private native String getLoginNick(int authCode);

    /**
     * 获取登录昵称
     *
     * @return 登录昵称
     */
    public String getLoginNick() {
        return getLoginNick(authCode);
    }

    private native int addLog(int authCode, int priority, String category, String content);

    /**
     * 不推荐使用本方法,请使用log开头的方法，参见see
     *
     * @param priority 级别，LOG_ 开头常量，留空为LOG_DEBUG
     * @param category 类型
     * @param content  内容
     * @return 状态码
     * @see #logTrace(String, String) 追踪信息
     * @see #logDebug(String, String) 调试信息
     * @see #logError(String, String) 错误信息
     * @see #logFatal(String, String) 致命信息
     * @see #logInfo(String, String) 日志信息
     * @see #logInfoRecv(String, String) 接收日志
     * @see #logInfoSend(String, String) 发送日志
     * @see #logInfoSuccess(String, String) 成功日志
     * @see #logWarning(String, String) 警告日志
     */
    @Deprecated
    public int addLog(int priority, String category, String content) {
        return status = addLog(authCode, priority, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：追踪<br>
     * 颜色：无
     * 提示：此日志不会输出到酷Q，只会记录到JCQ的控制台
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public abstract int logTrace(String category, String content);

    /**
     * 添加日志<br>
     * 级别：追踪<br>
     * 颜色：无
     * 提示：此日志不会输出到酷Q，只会记录到JCQ的控制台
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logTrace(String category, Throwable e) {
        int status = logTrace(category, e.getMessage());
        e.printStackTrace(System.out);
        return status;
    }

    /**
     * 添加日志<br>
     * 级别：追踪<br>
     * 颜色：无
     * 提示：此日志不会输出到酷Q，只会记录到JCQ的控制台
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logTrace(String category, String formatContent, Object... arguments) {
        return logTrace(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    /**
     * 添加日志<br>
     * 级别：调试<br>
     * 颜色：灰色
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public int logDebug(String category, String content) {
        return status = addLog(authCode, LOG_DEBUG, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：调试<br>
     * 颜色：灰色
     * 提示：此日志不会输出到酷Q，只会记录到JCQ的控制台
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logDebug(String category, Throwable e) {
        int status = logDebug(category, e.getMessage());
        e.printStackTrace(System.out);
        return status;
    }

    /**
     * 添加日志<br>
     * 级别：调试<br>
     * 颜色：灰色
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logDebug(String category, String formatContent, Object... arguments) {
        return logDebug(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    /**
     * 添加日志<br>
     * 级别：信息<br>
     * 颜色：黑色
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public int logInfo(String category, String content) {
        return status = addLog(authCode, LOG_INFO, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：信息<br>
     * 颜色：黑色
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logInfo(String category, Throwable e) {
        int status = logInfo(category, e.getMessage());
        e.printStackTrace(System.out);
        return status;
    }

    /**
     * 添加日志<br>
     * 级别：信息<br>
     * 颜色：黑色
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logInfo(String category, String formatContent, Object... arguments) {
        return logInfo(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    /**
     * 添加日志<br>
     * 级别：信息(接收)<br>
     * 颜色：蓝色
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public int logInfoRecv(String category, String content) {
        return status = addLog(authCode, LOG_INFORECV, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：信息(接收)<br>
     * 颜色：蓝色
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logInfoRecv(String category, Throwable e) {
        int status = logInfoRecv(category, e.getMessage());
        e.printStackTrace(System.out);
        return status;
    }

    /**
     * 添加日志<br>
     * 级别：信息(接收)<br>
     * 颜色：蓝色
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logInfoRecv(String category, String formatContent, Object... arguments) {
        return logInfoRecv(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    /**
     * 添加日志<br>
     * 级别：信息(发送)<br>
     * 颜色：绿色
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public int logInfoSend(String category, String content) {
        return status = addLog(authCode, LOG_INFOSEND, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：信息(发送)<br>
     * 颜色：绿色
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logInfoSend(String category, Throwable e) {
        int status = logInfoSend(category, e.getMessage());
        e.printStackTrace(System.out);
        return status;
    }

    /**
     * 添加日志<br>
     * 级别：信息(发送)<br>
     * 颜色：绿色
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logInfoSend(String category, String formatContent, Object... arguments) {
        return logInfoSend(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    /**
     * 添加日志<br>
     * 级别：信息(成功)<br>
     * 颜色：紫色
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public int logInfoSuccess(String category, String content) {
        return status = addLog(authCode, LOG_INFOSUCCESS, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：信息(成功)<br>
     * 颜色：紫色
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logInfoSuccess(String category, Throwable e) {
        int status = logInfoSuccess(category, e.getMessage());
        e.printStackTrace(System.out);
        return status;
    }

    /**
     * 添加日志<br>
     * 级别：信息(成功)<br>
     * 颜色：紫色
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logInfoSuccess(String category, String formatContent, Object... arguments) {
        return logInfoSuccess(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    /**
     * 添加日志<br>
     * 级别：警告<br>
     * 颜色：橙色
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public int logWarning(String category, String content) {
        return status = addLog(authCode, LOG_WARNING, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：警告<br>
     * 颜色：橙色
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logWarning(String category, Throwable e) {
        int status = logWarning(category, e.getMessage());
        e.printStackTrace(System.out);
        return status;
    }

    /**
     * 添加日志<br>
     * 级别：警告<br>
     * 颜色：橙色
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logWarning(String category, String formatContent, Object... arguments) {
        return logWarning(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    /**
     * 添加日志<br>
     * 级别：错误<br>
     * 颜色：红色
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public int logError(String category, String content) {
        return status = addLog(authCode, LOG_ERROR, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：错误<br>
     * 颜色：红色
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logError(String category, Throwable e) {
        int status = logError(category, e.getMessage());
        e.printStackTrace(System.out);
        return status;
    }

    /**
     * 添加日志<br>
     * 级别：错误<br>
     * 颜色：红色
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logError(String category, String formatContent, Object... arguments) {
        return logError(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    /**
     * 添加日志<br>
     * 级别：致命错误<br>
     * 颜色：深红
     *
     * @param category 类型
     * @param content  内容
     * @return 状态码
     */
    public int logFatal(String category, String content) {
        return status = addLog(authCode, LOG_FATAL, category, content);
    }

    /**
     * 添加日志<br>
     * 级别：致命错误<br>
     * 颜色：深红
     *
     * @param category 类型
     * @param e        异常
     * @return 状态码
     */
    public int logFatal(String category, Throwable e) {
        String pst = StringUtils.getStackTracePrintString(e);
        String sb = "很抱歉，应用发生致命错误，无法继续运行。\n" +
                "应用名称：[" + appName + "]\n" +
                "堆栈信息：\n" + pst;
        return logFatal(category, sb);
    }

    /**
     * 添加日志<br>
     * 级别：致命错误<br>
     * 颜色：深红
     *
     * @param category      类型
     * @param formatContent 格式
     * @param arguments     参数
     * @return 状态码
     */
    public int logFatal(String category, String formatContent, Object... arguments) {
        return logFatal(category, StringUtils.stringReplace(formatContent, "{}", arguments));
    }

    private native int sendPrivateMsg(int authCode, long qqId, String msg);

    /**
     * 发送私聊消息
     *
     * @param qqId 目标QQ
     * @param msg  消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    public int sendPrivateMsg(long qqId, String msg) {
        return status = sendPrivateMsg(authCode, qqId, msg);
    }

    /**
     * 发送私聊消息,不立即发送,创建消息处理对象
     *
     * @param qqId 目标QQ
     * @return 消息处理对象
     */
    public MsgBuilder sendPrivateMsg(long qqId) {
        return new MsgBuilder(this, AuthType.SendPrivateMsg, qqId);
    }

    private native int sendGroupMsg(int authCode, long groupId, String msg);

    /**
     * 发送群聊消息
     *
     * @param groupId 目标群号
     * @param msg     消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    public int sendGroupMsg(long groupId, String msg) {
        return status = sendGroupMsg(authCode, groupId, msg);
    }

    /**
     * 发送群聊消息,不立即发送,创建消息处理对象
     *
     * @param groupId 目标群号
     * @return 消息处理对象
     */
    public MsgBuilder sendGroupMsg(long groupId) {
        return new MsgBuilder(this, AuthType.SendGroupMsg, groupId);
    }

    private native int sendDiscussMsg(int authCode, long discussionId, String msg);

    /**
     * 发送讨论组消息
     *
     * @param discussionId 目标讨论组
     * @param msg          消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    public int sendDiscussMsg(long discussionId, String msg) {
        return status = sendDiscussMsg(authCode, discussionId, msg);
    }

    /**
     * 发送讨论组消息,不立即发送,创建消息处理对象
     *
     * @param discussionId 目标讨论组
     * @return 消息处理对象
     */
    public MsgBuilder sendDiscussMsg(long discussionId) {
        return new MsgBuilder(this, AuthType.SendDiscussMsg, discussionId);
    }

    private native int deleteMsg(int authCode, long msgId);

    /**
     * 撤回消息
     *
     * @param msgId 消息ID
     * @return 状态码
     */
    public int deleteMsg(long msgId) {
        return status = deleteMsg(authCode, msgId);
    }

    private native int sendLikeV2(int authCode, long qqId, int times);

    /**
     * 发送手机赞
     *
     * @param qqId  目标QQ
     * @param times 赞的次数,最多10次
     * @return 状态码
     */
    public int sendLikeV2(long qqId, int times) {
        return status = sendLikeV2(authCode, qqId, times <= 0 || times > 10 ? 1 : times);
    }

    /**
     * 发送手机赞,多次
     *
     * @param qqId  目标QQ
     * @param times 赞的次数,最多10次
     * @return 状态码
     */
    public int sendLike(long qqId, int times) {
        return status = sendLikeV2(authCode, qqId, times <= 0 || times > 10 ? 1 : times);
    }

    /**
     * 发送手机赞,赞一次,多次查看 {@link #sendLike(long, int) 发送多次手机赞}
     *
     * @param qqId 目标QQ
     * @return 状态码
     * @see #sendLike(long, int) 发送多次手机赞
     */
    public int sendLike(long qqId) {
        return status = sendLikeV2(authCode, qqId, 1);
    }

    private native String getCookies(int authCode);

    /**
     * 获取Cookie,慎用,此接口需要严格授权
     *
     * @return Cookies
     */
    public String getCookies() {
        return getCookies(authCode);
    }

    private native int getCsrfToken(int authCode);

    /**
     * 获取CsrfToken,即QQ网页用到的bkn/g_tk等 慎用,此接口需要严格授权
     *
     * @return CsrfToken
     */
    public int getCsrfToken() {
        return status = getCsrfToken(authCode);
    }

    private native String getRecord(int authCode, String file, String outformat);

    /**
     * 接收消息中的语音(record)
     *
     * @param file      收到消息中的语音文件名(file)
     * @param outformat 应用所需的语音文件格式，目前支持 mp3,amr,wma,m4a,spx,ogg,wav,flac
     * @return 返回保存在 \data\record\ 目录下的文件名
     */
    public String getRecord(String file, String outformat) {
        return getRecord(authCode, file, outformat);
    }

    private native int setGroupKick(int authCode, long groupId, long qqId, boolean notBack);

    /**
     * 移除群员
     *
     * @param groupId 目标群
     * @param qqId    目标QQ
     * @param notBack 如果为true，则“不再接收此人加群申请”，请慎用
     * @return 状态码
     */
    public int setGroupKick(long groupId, long qqId, boolean notBack) {
        return status = setGroupKick(authCode, groupId, qqId, notBack);
    }

    private native int setGroupBan(int authCode, long groupId, long qqId, long banTime);

    /**
     * 禁言群员
     *
     * @param groupId 群号
     * @param qqId    目标QQ
     * @param banTime 禁言的时间，单位为秒。如果要解禁，这里填写0
     * @return 状态码
     */
    public int setGroupBan(long groupId, long qqId, long banTime) {
        return status = setGroupBan(authCode, groupId, qqId, banTime);
    }

    private native int setGroupAdmin(int authCode, long groupId, long qqId, boolean isAdmin);

    /**
     * 设置群管理员
     *
     * @param groupId 目标群
     * @param qqId    目标QQ
     * @param isAdmin true/设置管理员 false/取消管理员
     * @return 状态码
     */
    public int setGroupAdmin(long groupId, long qqId, boolean isAdmin) {
        return status = setGroupAdmin(authCode, groupId, qqId, isAdmin);
    }

    private native int setGroupWholeBan(int authCode, long groupId, boolean isBan);

    /**
     * 全群禁言
     *
     * @param groupId 目标群
     * @param isBan   true/开启 false/关闭
     * @return 状态码
     */
    public int setGroupWholeBan(long groupId, boolean isBan) {
        return status = setGroupWholeBan(authCode, groupId, isBan);
    }

    private native int setGroupAnonymousBan(int authCode, long groupId, String anonymous, long banTime);

    /**
     * 禁言匿名群员
     *
     * @param groupId   目标群
     * @param anonymous 群消息事件收到的“fromAnonymous”参数
     * @param banTime   禁言的时间，单位为秒。不支持解禁
     * @return 状态码
     */
    public int setGroupAnonymousBan(long groupId, String anonymous, long banTime) {
        return status = setGroupAnonymousBan(authCode, groupId, anonymous, banTime);
    }

    private native int setGroupAnonymous(int authCode, long groupId, boolean isAnonymous);

    /**
     * 群匿名设置
     *
     * @param groupId     目标群
     * @param isAnonymous true/开启 false/关闭
     * @return 状态码
     */
    public int setGroupAnonymous(long groupId, boolean isAnonymous) {
        return status = setGroupAnonymous(authCode, groupId, isAnonymous);
    }

    private native int setGroupCard(int authCode, long groupId, long qqId, String nick);

    /**
     * 设置群成员名片
     *
     * @param groupId 目标群
     * @param qqId    目标QQ
     * @param nick    新名片(昵称)
     * @return 状态码
     */
    public int setGroupCard(long groupId, long qqId, String nick) {
        return status = setGroupCard(authCode, groupId, qqId, nick);
    }

    private native int setGroupLeave(int authCode, long groupId, boolean isDisband);

    /**
     * 退出QQ群,慎用,此接口需要严格授权
     *
     * @param groupId   目标群
     * @param isDisband 默认为假 true/解散本群(群主) false/退出本群(管理、群成员)
     * @return 状态码
     */
    public int setGroupLeave(long groupId, boolean isDisband) {
        return status = setGroupLeave(authCode, groupId, isDisband);
    }

    private native int setGroupSpecialTitle(int authCode, long groupId, long qqId, String title, long expireTime);

    /**
     * 设置群成员专属头衔,需群主权限
     *
     * @param groupId    目标群
     * @param qqId       目标QQ
     * @param title      头衔，如果要删除，这里填空
     * @param expireTime 专属头衔有效期，单位为秒。如果永久有效，这里填写-1
     * @return 状态码
     */
    public int setGroupSpecialTitle(long groupId, long qqId, String title, long expireTime) {
        if (StringUtils.isEmpty(title)) {
            title = "";
        }
        return status = setGroupSpecialTitle(authCode, groupId, qqId, title, expireTime);
    }

    private native byte[] getGroupMemberInfoV2(int authCode, long groupId, long qqId, boolean notCache);

    /**
     * 获取群成员信息 (v2版本)
     *
     * @param groupId  目标QQ所在群
     * @param qqId     目标QQ
     * @param notCache 不使用缓存，通常忽略本参数，仅在必要时使用
     * @return 如果成功，返回群成员信息，失败返回null
     */
    public Member getGroupMemberInfoV2(long groupId, long qqId, boolean notCache) {
        return Member.toMember(getGroupMemberInfoV2(authCode, groupId, qqId, notCache));
    }

    /**
     * 获取群成员信息 (v2版本)
     *
     * @param groupId 目标QQ所在群
     * @param qqId    目标QQ
     * @return 如果成功，返回群成员信息，失败返回null
     */
    public Member getGroupMemberInfoV2(long groupId, long qqId) {
        return getGroupMemberInfoV2(groupId, qqId, false);
    }

    /**
     * 获取群成员信息
     *
     * @param member   群成员信息对象，用于覆盖
     * @param groupId  目标QQ所在群
     * @param qqId     目标QQ
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 如果成功，返回群成员信息，失败返回null
     */
    public Member getGroupMemberInfo(Member member, long groupId, long qqId, boolean notCache) {
        return Member.toMember(getGroupMemberInfoV2(authCode, groupId, qqId, notCache));
    }

    /**
     * 获取群成员信息
     *
     * @param groupId  目标QQ所在群
     * @param qqId     目标QQ
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 如果成功，返回群成员信息，失败返回null
     */
    public Member getGroupMemberInfo(long groupId, long qqId, boolean notCache) {
        return Member.toMember(getGroupMemberInfoV2(authCode, groupId, qqId, notCache));
    }

    /**
     * 获取群成员信息
     *
     * @param groupId 目标QQ所在群
     * @param qqId    目标QQ
     * @return 如果成功，返回群成员信息，失败返回null
     */
    public Member getGroupMemberInfo(long groupId, long qqId) {
        return getGroupMemberInfo(groupId, qqId, false);
    }

    private native byte[] getStrangerInfo(int authCode, long qqId, boolean notCache);

    /**
     * 获取陌生人信息
     *
     * @param info     陌生人信息对象，用于覆盖
     * @param qqId     目标QQ
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 如果成功，返回陌生人信息
     */
    public QQInfo getStrangerInfo(QQInfo info, long qqId, boolean notCache) {
        return QQInfo.toQQInfo(getStrangerInfo(authCode, qqId, notCache), info);
    }

    /**
     * 获取陌生人信息
     *
     * @param qqId     目标QQ
     * @param notCache 是否不使用缓存，通常忽略本参数(false)，仅在必要时使用
     * @return 如果成功，返回陌生人信息
     */
    public QQInfo getStrangerInfo(long qqId, boolean notCache) {
        return QQInfo.toQQInfo(getStrangerInfo(authCode, qqId, notCache));
    }

    /**
     * 获取陌生人信息
     *
     * @param qqId 目标QQ
     * @return 如果成功，返回陌生人信息
     */
    public QQInfo getStrangerInfo(long qqId) {
        return getStrangerInfo(qqId, false);
    }

    private native int setDiscussLeave(int authCode, long discussionId);

    /**
     * 退出讨论组
     *
     * @param discussionId 目标讨论组
     * @return 状态码
     */
    public int setDiscussLeave(long discussionId) {
        return status = setDiscussLeave(authCode, discussionId);
    }

    private native int setFriendAddRequest(int authCode, String responseFlag, int backType, String remarks);

    /**
     * 处理好友添加请求
     *
     * @param responseFlag 请求事件收到的“responseFlag”参数
     * @param backType     REQUEST_ADOPT(通过) 或 REQUEST_REFUSE(拒绝)
     * @param remarks      添加后的好友备注
     * @return 状态码
     */
    public int setFriendAddRequest(String responseFlag, int backType, String remarks) {
        return status = setFriendAddRequest(authCode, responseFlag, backType, remarks);
    }

    private native int setGroupAddRequestV2(int authCode, String responseFlag, int requestType, int backType, String reason);

    /**
     * 处理群添加请求
     *
     * @param responseFlag 请求事件收到的“responseFlag”参数
     * @param requestType  根据请求事件的子类型区分 REQUEST_GROUP_ADD(群添加) 或 REQUEST_GROUP_INVITE(群邀请)
     * @param backType     REQUEST_ADOPT(通过) 或 REQUEST_REFUSE(拒绝)
     * @param reason       操作理由，仅 REQUEST_GROUP_ADD(群添加) 且 REQUEST_REFUSE(拒绝) 时可用
     * @return 状态码
     */
    public int setGroupAddRequestV2(String responseFlag, int requestType, int backType, String reason) {
        return status = setGroupAddRequestV2(authCode, responseFlag, requestType, backType, reason);
    }

    /**
     * 处理群添加请求
     *
     * @param responseFlag 请求事件收到的“responseFlag”参数
     * @param requestType  根据请求事件的子类型区分 REQUEST_GROUP_ADD(群添加) 或 REQUEST_GROUP_INVITE(群邀请)
     * @param backType     REQUEST_ADOPT(通过) 或 REQUEST_REFUSE(拒绝)
     * @param reason       操作理由，仅 REQUEST_GROUP_ADD(群添加) 且 REQUEST_REFUSE(拒绝) 时可用
     * @return 状态码
     */
    public int setGroupAddRequest(String responseFlag, int requestType, int backType, String reason) {
        return status = setGroupAddRequestV2(authCode, responseFlag, requestType, backType, reason);
    }

    private native byte[] getGroupMemberList(int authCode, long groupId);

    /**
     * 获取群成员列表
     *
     * @param groupId 目标群
     * @return 如果成功，返回群成员列表
     */
    public List<Member> getGroupMemberList(long groupId) {
        return toMemberList(getGroupMemberList(authCode, groupId));
    }

    private native byte[] getGroupList(int authCode);

    /**
     * 获取群列表
     *
     * @return 如果成功，返回群列表
     */
    public List<Group> getGroupList() {
        return toGroupList(getGroupList(authCode));
    }

    /**
     * base64解码
     *
     * @param code 要解析的代码
     * @return 解析好的byte数组
     */
    private native byte[] base64Decode(String code);

    /**
     * 获取匿名信息
     *
     * @param source 源数据
     * @return 如果成功，返回匿名信息
     */
    public Anonymous getAnonymous(String source) {
        return Anonymous.toAnonymous(base64Decode(source));
    }

    /**
     * 获取群文件信息
     *
     * @param source 源数据
     * @return 如果成功，返回群文件信息
     */
    public GroupFile getGroupFile(String source) {
        return GroupFile.toGroupFile(base64Decode(source));
    }

    /**
     * 转换数据到群信息
     *
     * @param source 数据
     * @return 群列表
     */
    private List<Group> toGroupList(byte[] source) {
        List<Group> list = new ArrayList<Group>();
        if (source == null || source.length < 4)
            return list;
        Pack pack = new Pack(source);
        int count = pack.getInt();
        for (int i = 0; i < count; i++) {
            if (pack.getRemainingLen() <= 0) {
                return list;
            }
            Group group = Group.toGroup(pack.getToken());
            if (group == null)
                return list;
            list.add(group);
        }
        return list;
    }

    private native Font toFont(int font);

    /**
     * 转换数据到字体信息
     *
     * @param font 字体
     * @return 字体信息
     */
    public Font getFont(int font) {
        return toFont(font);
    }

    /**
     * 数据转群成员
     *
     * @param source 数据
     * @return 群成员列表
     */
    private List<Member> toMemberList(byte[] source) {
        List<Member> list = new ArrayList<Member>();
        if (source == null || source.length < 4)
            return list;
        Pack pack = new Pack(source);
        int count = pack.getInt();
        for (int i = 0; i < count; i++) {
            if (pack.getRemainingLen() <= 0) {
                return list;
            }
            Member member = Member.toMember(pack.getToken());
            if (member == null)
                return list;
            list.add(member);
        }
        return list;
    }

    /**
     * 获取最后状态
     *
     * @return 描述信息
     */
    public CQStatus getLastStatus() {
        return CQStatus.getStatus(status < 0 ? status : 0);
    }

}
