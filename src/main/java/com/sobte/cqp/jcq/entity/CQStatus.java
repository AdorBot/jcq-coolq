package com.sobte.cqp.jcq.entity;

/**
 * Created by Sobte on 2018/4/24.<br>
 * Time: 2018/4/24 17:13<br>
 * Email: i@sobte.me<br>
 * 酷Q状态码
 *
 * @author Sobte
 */
public class CQStatus {

    private int id;
    private String msg;

    public CQStatus(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 操作是否执行成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return id == 0;
    }

    /**
     * 获取酷Q执行状态
     *
     * @param status 状态码
     * @return 酷Q状态
     */
    public static CQStatus getStatus(int status) {
        String msg = "未知错误";
        switch (status) {
            case 0:
                msg = "执行成功";
                break;
            case -1:
                msg = "请求发送失败";
                break;
            case -2:
                msg = "未收到服务器回复，可能未发送成功";
                break;
            case -3:
                msg = "消息过长或为空";
                break;
            case -4:
                msg = "消息解析过程异常";
                break;
            case -5:
                msg = "日志功能未启用";
                break;
            case -6:
                msg = "日志优先级错误";
                break;
            case -7:
                msg = "数据入库失败";
                break;
            case -8:
                msg = "不支持对系统帐号操作";
                break;
            case -9:
                msg = "帐号不在该群内，消息无法发送";
                break;
            case -10:
                msg = "该用户不存在/不在群内";
                break;
            case -11:
                msg = "数据错误，无法请求发送";
                break;
            case -12:
                msg = "不支持对匿名成员解除禁言";
                break;
            case -13:
                msg = "无法解析要禁言的匿名成员数据";
                break;
            case -14:
                msg = "由于未知原因，操作失败";
                break;
            case -15:
                msg = "群未开启匿名发言功能，或匿名帐号被禁言";
                break;
            case -16:
                msg = "帐号不在群内或网络错误，无法退出/解散该群";
                break;
            case -17:
                msg = "帐号为群主，无法退出该群";
                break;
            case -18:
                msg = "帐号非群主，无法解散该群";
                break;
            case -19:
                msg = "临时消息已失效或未建立";
                break;
            case -20:
                msg = "参数错误";
                break;
            case -21:
                msg = "临时消息已失效或未建立";
                break;
            case -22:
                msg = "获取QQ信息失败";
                break;
            case -23:
                msg = "找不到与目标QQ的关系，消息无法发送";
                break;
            case -30:
                msg = "消息被服务器拒绝，请检查参数是否正确或稍候再试";
                break;
            case -99:
                msg = "您调用的功能无法在此版本上实现";
                break;
            case -101:
                msg = "应用过大";
                break;
            case -102:
                msg = "不是合法的应用";
                break;
            case -103:
                msg = "不是合法的应用";
                break;
            case -104:
                msg = "应用不存在公开的Information函数";
                break;
            case -105:
                msg = "无法载入应用信息";
                break;
            case -106:
                msg = "文件名与应用ID不同";
                break;
            case -107:
                msg = "返回信息解析错误";
                break;
            case -108:
                msg = "AppInfo返回的Api版本不支持直接加载，仅支持Api版本为9(及以上)的应用直接加载";
                break;
            case -109:
                msg = "AppInfo返回的AppID错误";
                break;
            case -110:
                msg = "缺失AppInfo返回的AppID对应的[Appid].json文件";
                break;
            case -111:
                msg = "[Appid].json文件内的AppID与其文件名不同";
                break;
            case -120:
                msg = "无Api授权接收函数(Initialize)";
                break;
            case -121:
                msg = "Api授权接收函数(Initialize)返回值非0";
                break;
            case -122:
                msg = "尝试恶意修改酷Q配置文件，将取消加载并关闭酷Q";
                break;
            case -150:
                msg = "无法载入应用信息";
                break;
            case -151:
                msg = "应用信息Json串解析失败，请检查Json串是否正确";
                break;
            case -152:
                msg = "Api版本过旧或过新";
                break;
            case -153:
                msg = "应用信息错误或存在缺失";
                break;
            case -154:
                msg = "Appid不合法";
                break;
            case -160:
                msg = "事件类型(Type)错误或缺失";
                break;
            case -161:
                msg = "事件函数(Function)错误或缺失";
                break;
            case -162:
                msg = "应用优先级不为10000、20000、30000、40000中的一个";
                break;
            case -163:
                msg = "事件类型(Api)不支持应用Api版本";
                break;
            case -164:
                msg = "应用Api版本大于8，但使用了新版本已停用的事件类型(Type)：1(好友消息)、3(临时消息)";
                break;
            case -165:
                msg = "事件类型为2(群消息)、4(讨论组消息)、21(私聊消息)，但缺少正则表达式(regex)的表达式部分(expression)";
                break;
            case -166:
                msg = "存在为空的正则表达式(regex)的key";
                break;
            case -167:
                msg = "存在为空的正则表达式(regex)的表达式部分(expression)";
                break;
            case -168:
                msg = "应用事件(event)id参数不存在或为0";
                break;
            case -169:
                msg = "应用事件(event)id参数有重复";
                break;
            case -180:
                msg = "应用状态(status)id参数不存在或为0";
                break;
            case -181:
                msg = "应用状态(status)period参数不存在或设置错误";
                break;
            case -182:
                msg = "应用状态(status)id参数有重复";
                break;
            case -201:
                msg = "无法载入应用，可能是应用文件已损坏";
                break;
            case -202:
                msg = "Api版本过旧或过新";
                break;
            case -997:
                msg = "应用未启用";
                break;
            case -998:
                msg = "应用调用在Auth声明之外的 酷Q Api。";
                break;
        }
        return new CQStatus(status, msg);
    }

    @Override
    public String toString() {
        return "CQStatus{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }

}
