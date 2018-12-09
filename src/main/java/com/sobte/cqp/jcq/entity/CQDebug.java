package com.sobte.cqp.jcq.entity;

import com.sobte.cqp.jcq.util.StringUtils;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

/**
 * Created by Sobte on 2018/3/11.
 * Time: 3:34
 * Email: i@sobte.me
 * 此类用于不在酷Q模式下调试用
 *
 * @author Sobte
 * @version 1.2.6
 */
public class CQDebug extends CoolQ {

    /**
     * 默认的CQDebug
     */
    private static final CQDebug DEBUG = new CQDebug();

    /**
     * 获取默认的CQDebug，避免创建多个相同的DEBUG，造成内存的开销过大
     *
     * @return 默认的CQDebug
     */
    public static CoolQ getInstance() {
        return DEBUG;
    }

    public CQDebug() {
        super(1000);
    }

    public CQDebug(String appDirectory, String appName) {
        super(1000, appDirectory, appName);
    }

    @Override
    public int setFatal(String errorInfo) {
        System.err.printf("发生致命错误 错误信息：%s%n", errorInfo);
        return status = 0;
    }

    /**
     * 获取应用目录
     *
     * @return 应用目录, 返回的路径末尾带"\"
     */
    @Override
    public String getAppDirectory() {
        if (StringUtils.isEmpty(appDirectory)) {
            appDirectory = new File("").getAbsolutePath() + File.separator;
        }
        File file = new File(appDirectory);
        if (!file.isDirectory())
            file.mkdirs();
        addLogs(LOG_INFO, "取应用目录", String.format("返回：%s", appDirectory));
        return appDirectory;
    }

    /**
     * 获取登录QQ
     *
     * @return 登录QQ
     */
    @Override
    public long getLoginQQ() {
        addLogs(LOG_INFO, "取登录QQ", "返回：酷Q");
        return 10001L;
    }

    /**
     * 获取登录昵称
     *
     * @return 登录昵称
     */
    @Override
    public String getLoginNick() {
        addLogs(LOG_INFO, "取登录昵称", "返回：酷Q");
        return "酷Q";
    }

    protected int addLogs(int priority, String category, String content) {
        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        Thread thread = Thread.currentThread();
        // pid
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        sb.append(sdf.format(date));
        switch (priority) {
            case LOG_TRACE:
                fmt.format(" %11s ", "TRACE");
                break;
            case LOG_DEBUG:
                fmt.format(" %11s ", "DEBUG");
                break;
            case LOG_ERROR:
                fmt.format(" %11s ", "ERROR");
                break;
            case LOG_FATAL:
                fmt.format(" %11s ", "FATAL");
                break;
            default:
            case LOG_INFO:
                fmt.format(" %11s ", "INFO");
                break;
            case LOG_INFORECV:
                fmt.format(" %11s ", "INFORECV");
                break;
            case LOG_INFOSEND:
                fmt.format(" %11s ", "INFOSEND");
                break;
            case LOG_INFOSUCCESS:
                fmt.format(" %11s ", "INFOSUCCESS");
                break;
            case LOG_WARNING:
                fmt.format(" %11s ", "WARNING");
                break;
        }
        sb.append(pid);
        sb.append(" --- [");
        fmt.format("%15s", thread.getName());
        sb.append("] ");
        fmt.format("%-19s", category);
        sb.append(" : ");
        sb.append(content);
        System.out.println(sb.toString());
        return status = 0;
    }

    /**
     * 发送手机赞
     *
     * @param qqId  目标QQ
     * @param times 赞的次数,最多10次
     * @return 状态码
     */
    @Override
    public int sendLike(long qqId, int times) {
        return sendLikeV2(qqId, times);
    }

    /**
     * 获取群成员信息 (v2版本)
     *
     * @param groupId 目标QQ所在群
     * @param qqId    目标QQ
     * @return 如果成功，返回群成员信息，失败返回null
     */
    @Override
    public Member getGroupMemberInfoV2(long groupId, long qqId) {
        return getGroupMemberInfoV2(groupId, qqId, false);
    }

    /**
     * 获取群成员信息
     *
     * @param groupId  目标QQ所在群
     * @param qqId     目标QQ
     * @param notCache 不使用缓存，通常忽略本参数，仅在必要时使用
     * @return 如果成功，返回群成员信息，失败返回null
     */
    @Override
    public Member getGroupMemberInfo(long groupId, long qqId, boolean notCache) {
        return getGroupMemberInfoV2(groupId, qqId, notCache);
    }

    /**
     * 获取群成员信息
     *
     * @param groupId 目标QQ所在群
     * @param qqId    目标QQ
     * @return 如果成功，返回群成员信息，失败返回null
     */
    @Override
    public Member getGroupMemberInfo(long groupId, long qqId) {
        return getGroupMemberInfo(groupId, qqId, false);
    }

    /**
     * 获取陌生人信息
     *
     * @param qqId 目标QQ
     * @return 如果成功，返回陌生人信息
     */
    @Override
    public QQInfo getStrangerInfo(long qqId) {
        return getStrangerInfo(qqId, false);
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
    @Override
    public int setGroupAddRequest(String responseFlag, int requestType, int backType, String reason) {
        return setGroupAddRequestV2(responseFlag, requestType, backType, reason);
    }

    /**
     * 获取匿名信息
     *
     * @param source 源数据
     * @return 如果成功，返回匿名信息
     */
    @Override
    public Anonymous getAnonymous(String source) {
        addLogs(LOG_INFO, "取匿名信息", String.format("本函数请在酷Q中测试 源数据：%s 返回：测试匿名信息", source));
        Anonymous anonymous = new Anonymous();
        anonymous.setAid(1000L);
        anonymous.setName("大力鬼王");
        anonymous.setToken(new byte[0]);
        return anonymous;
    }

    /**
     * 获取群文件信息
     *
     * @param source 源数据
     * @return 如果成功，返回群文件信息
     */
    @Override
    public GroupFile getGroupFile(String source) {
        addLogs(LOG_INFO, "取群文件信息", String.format("本函数请在酷Q中测试 源数据：%s 返回：测试文件信息", source));
        GroupFile groupFile = new GroupFile();
        groupFile.setId("233");
        groupFile.setBusid(233);
        groupFile.setName("233");
        return groupFile;
    }

    /**
     * 转换数据到字体信息
     *
     * @param font 字体
     * @return 字体信息
     */
    @Override
    public Font getFont(int font) {
        Font f = new Font();
        f.setName("Microsoft YaHei");
        return f;
    }

    /**
     * 获取最后状态
     *
     * @return 描述信息
     */
    @Override
    public CQStatus getLastStatus() {
        return super.getLastStatus();
    }

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
        return addLogs(priority, category, content);
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
    @Override
    public int logTrace(String category, String content) {
        return addLogs(LOG_TRACE, category, content);
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
        return addLogs(LOG_DEBUG, category, content);
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
        return addLogs(LOG_INFO, category, content);
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
        return addLogs(LOG_INFORECV, category, content);
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
        return addLogs(LOG_INFOSEND, category, content);
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
        return addLogs(LOG_INFOSUCCESS, category, content);
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
        return addLogs(LOG_WARNING, category, content);
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
        return addLogs(LOG_ERROR, category, content);
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
        return addLogs(LOG_FATAL, category, content);
    }

    /**
     * 发送私聊消息
     *
     * @param qqId 目标QQ
     * @param msg  消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    @Override
    public int sendPrivateMsg(long qqId, String msg) {
        addLogs(LOG_INFO, "发送私聊消息", String.format("q[%15s] %s", qqId, msg));
        return status = 0;
    }

    /**
     * 发送群消息
     *
     * @param groupId 目标群
     * @param msg     消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    @Override
    public int sendGroupMsg(long groupId, String msg) {
        addLogs(LOG_INFO, "发送群聊消息", String.format("g[%15s] %s", groupId, msg));
        return status = 0;
    }

    /**
     * 发送讨论组消息
     *
     * @param discussionId 目标讨论组
     * @param msg          消息内容
     * @return 失败返回负值, 成功返回消息ID
     */
    @Override
    public int sendDiscussMsg(long discussionId, String msg) {
        addLogs(LOG_INFO, "发送讨论组消息", String.format("d[%15s] %s", discussionId, msg));
        return status = 0;
    }

    /**
     * 撤回消息
     *
     * @param msgId 消息ID
     * @return 状态码
     */
    @Override
    public int deleteMsg(long msgId) {
        addLogs(LOG_INFO, "撤回消息", String.format("m[%15s]", msgId));
        return status = 0;
    }

    /**
     * 发送手机赞
     *
     * @param qqId  目标QQ
     * @param times 赞的次数,最多10次
     * @return 状态码
     */
    @Override
    public int sendLikeV2(long qqId, int times) {
        addLogs(LOG_INFO, "发送赞", String.format("q[%15s] %s次", qqId, times <= 0 || times > 10 ? 1 : times));
        return status = 0;
    }

    /**
     * 获取Cookie,慎用,此接口需要严格授权
     *
     * @return Cookies
     */
    @Override
    public String getCookies() {
        addLogs(LOG_INFO, "取Cookies", "本函数请在酷Q中测试");
        return "";
    }

    /**
     * 获取CsrfToken,即QQ网页用到的bkn/g_tk等 慎用,此接口需要严格授权
     *
     * @return CsrfToken
     */
    @Override
    public int getCsrfToken() {
        addLogs(LOG_INFO, "取CsrfToken", "本函数请在酷Q中测试");
        return status = 0;
    }

    /**
     * 接收消息中的语音(record)
     *
     * @param file      收到消息中的语音文件名(file)
     * @param outformat 应用所需的语音文件格式，目前支持 mp3,amr,wma,m4a,spx,ogg,wav,flac
     * @return 返回保存在 \data\record\ 目录下的文件名
     */
    @Override
    public String getRecord(String file, String outformat) {
        addLogs(LOG_INFO, "接收语音", String.format("本函数请在酷Q中测试 文件名：%s 指定格式：%s", file, outformat));
        return "";
    }

    /**
     * 移除群员
     *
     * @param groupId 目标群
     * @param qqId    目标QQ
     * @param notBack 如果为true，则“不再接收此人加群申请”，请慎用
     * @return 状态码
     */
    @Override
    public int setGroupKick(long groupId, long qqId, boolean notBack) {
        addLogs(LOG_INFO, "移除群员", String.format("g[%15s] q[%15s] 拒绝再加群：%s", groupId, qqId, notBack));
        return status = 0;
    }

    /**
     * 禁言群员
     *
     * @param groupId 群号
     * @param qqId    目标QQ
     * @param banTime 禁言的时间，单位为秒。如果要解禁，这里填写0
     * @return 状态码
     */
    @Override
    public int setGroupBan(long groupId, long qqId, long banTime) {
        addLogs(LOG_INFO, "禁言群员", String.format("g[%15s] q[%15s] 禁言时间：%s", groupId, qqId, banTime));
        return status = 0;
    }

    /**
     * 设置群管理员
     *
     * @param groupId 目标群
     * @param qqId    目标QQ
     * @param isAdmin true/设置管理员 false/取消管理员
     * @return 状态码
     */
    @Override
    public int setGroupAdmin(long groupId, long qqId, boolean isAdmin) {
        addLogs(LOG_INFO, "设置群管理", String.format("g[%15s] q[%15s] 成为管理员：%s", groupId, qqId, isAdmin));
        return status = 0;
    }

    /**
     * 全群禁言
     *
     * @param groupId 目标群
     * @param isBan   true/开启 false/关闭
     * @return 状态码
     */
    @Override
    public int setGroupWholeBan(long groupId, boolean isBan) {
        addLogs(LOG_INFO, "全群禁言", String.format("g[%15s] 开启禁言：%s", groupId, isBan));
        return status = 0;
    }

    /**
     * 禁言匿名群员
     *
     * @param groupId   目标群
     * @param anonymous 群消息事件收到的“fromAnonymous”参数
     * @param banTime   禁言的时间，单位为秒。不支持解禁
     * @return 状态码
     */
    @Override
    public int setGroupAnonymousBan(long groupId, String anonymous, long banTime) {
        addLogs(LOG_INFO, "全群禁言", String.format("g[%15s] 匿名：%s 禁言时间：%s", groupId, anonymous, banTime));
        return status = 0;
    }

    /**
     * 群匿名设置
     *
     * @param groupId     目标群
     * @param isAnonymous true/开启 false/关闭
     * @return 状态码
     */
    @Override
    public int setGroupAnonymous(long groupId, boolean isAnonymous) {
        addLogs(LOG_INFO, "群匿名设置", String.format("g[%15s] 开启匿名：%s", groupId, isAnonymous));
        return status = 0;
    }

    /**
     * 设置群成员名片
     *
     * @param groupId 目标群
     * @param qqId    目标QQ
     * @param nick    新名片(昵称)
     * @return 状态码
     */
    @Override
    public int setGroupCard(long groupId, long qqId, String nick) {
        addLogs(LOG_INFO, "设置群成员名片", String.format("g[%15s] q[%15s] 新名片：%s", groupId, qqId, nick));
        return status = 0;
    }

    /**
     * 退出QQ群,慎用,此接口需要严格授权
     *
     * @param groupId   目标群
     * @param isDisband 默认为假 true/解散本群(群主) false/退出本群(管理、群成员)
     * @return 状态码
     */
    @Override
    public int setGroupLeave(long groupId, boolean isDisband) {
        addLogs(LOG_INFO, "退出QQ群", String.format("g[%15s] 是否解散：%s", groupId, isDisband));
        return status = 0;
    }

    /**
     * 设置群成员专属头衔,需群主权限
     *
     * @param groupId    目标群
     * @param qqId       目标QQ
     * @param title      头衔，如果要删除，这里填空
     * @param expireTime 专属头衔有效期，单位为秒。如果永久有效，这里填写-1
     * @return 状态码
     */
    @Override
    public int setGroupSpecialTitle(long groupId, long qqId, String title, long expireTime) {
        addLogs(LOG_INFO, "设置群成员专属头衔", String.format("g[%15s] q[%15s] 头衔：%s 过期时间：%s", groupId, qqId, title, expireTime));
        return status = 0;
    }

    /**
     * 获取群成员信息
     *
     * @param groupId  目标QQ所在群
     * @param qqId     目标QQ
     * @param notCache 不使用缓存，通常忽略本参数，仅在必要时使用
     * @return 如果成功，返回群成员信息，失败返回null
     */
    @Override
    public Member getGroupMemberInfoV2(long groupId, long qqId, boolean notCache) {
        addLogs(LOG_INFO, "取群成员信息", String.format("g[%15s] q[%15s] 本函数请在酷Q中测试 不使用缓存：%s 返回：测试群成员", groupId, qqId, notCache));
        Member member = new Member();
        member.setGroupId(groupId);
        member.setQqId(qqId);
        member.setNick("测试昵称");
        member.setCard("测试名片");
        member.setGender(0);
        member.setAuthority(1);
        member.setTitle("测试专属头衔");
        member.setTitleExpire(null);
        return member;
    }

    /**
     * 获取陌生人信息
     *
     * @param qqId     目标QQ
     * @param notCache 不使用缓存，通常忽略本参数，仅在必要时使用
     * @return 如果成功，返回陌生人信息
     */
    @Override
    public QQInfo getStrangerInfo(long qqId, boolean notCache) {
        addLogs(LOG_INFO, "取陌生人信息", String.format("q[%15s] 本函数请在酷Q中测试 不使用缓存：%s 返回：测试陌生人", qqId, notCache));
        QQInfo info = new QQInfo();
        info.setQqId(qqId);
        info.setNick("测试昵称");
        info.setAge(0);
        info.setGender(0);
        return info;
    }

    /**
     * 获取群成员列表
     *
     * @param groupId 目标群
     * @return 如果成功，返回群成员列表
     */
    @Override
    public List<Member> getGroupMemberList(long groupId) {
        addLogs(LOG_INFO, "取群成员列表", String.format("g[%15s] 本函数请在酷Q中测试 返回：空列表", groupId));
        return new ArrayList<Member>();
    }

    /**
     * 获取群列表
     *
     * @return 如果成功，返回群列表
     */
    @Override
    public List<Group> getGroupList() {
        addLogs(LOG_INFO, "取群列表", "本函数请在酷Q中测试 返回：空列表");
        return new ArrayList<Group>();
    }

    /**
     * 退出讨论组
     *
     * @param discussionId 目标讨论组
     * @return 状态码
     */
    @Override
    public int setDiscussLeave(long discussionId) {
        addLogs(LOG_INFO, "退出讨论组", String.format("d[%15s]", discussionId));
        return status = 0;
    }

    /**
     * 处理好友添加请求
     *
     * @param responseFlag 请求事件收到的“responseFlag”参数
     * @param backType     REQUEST_ADOPT(通过) 或 REQUEST_REFUSE(拒绝)
     * @param remarks      添加后的好友备注
     * @return 状态码
     */
    @Override
    public int setFriendAddRequest(String responseFlag, int backType, String remarks) {
        addLogs(LOG_INFO, "处理好友添加请求", String.format("请求反馈标识:%s 反馈类型:%s 备注:%s", responseFlag, backType, remarks));
        return status = 0;
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
    @Override
    public int setGroupAddRequestV2(String responseFlag, int requestType, int backType, String reason) {
        addLogs(LOG_INFO, "处理群添加请求", String.format("请求反馈标识:%s 请求类型:%s 反馈类型:%s 理由:%s", responseFlag, requestType, backType, reason));
        return status = 0;
    }

}