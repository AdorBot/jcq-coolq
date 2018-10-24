package com.sobte.cqp.jcq.entity;

/**
 * Created by Sobte on 2018/1/31.
 * Time: 22:32
 * Email: i@sobte.me
 * 日志
 */
public interface ILog {

    // 日志变量
    /**
     * 级别：追踪<br>
     * 颜色：无
     */
    int LOG_TRACE = -10;
    /**
     * 级别：调试<br>
     * 颜色：灰色
     */
    int LOG_DEBUG = 0;
    /**
     * 级别：信息<br>
     * 颜色：黑色
     */
    int LOG_INFO = 10;
    /**
     * 级别：信息(成功)<br>
     * 颜色：紫色
     */
    int LOG_INFOSUCCESS = 11;
    /**
     * 级别：信息(接收)<br>
     * 颜色：蓝色
     */
    int LOG_INFORECV = 12;
    /**
     * 级别：信息(发送)<br>
     * 颜色：绿色
     */
    int LOG_INFOSEND = 13;
    /**
     * 级别：警告<br>
     * 颜色：橙色
     */
    int LOG_WARNING = 20;
    /**
     * 级别：错误<br>
     * 颜色：红色
     */
    int LOG_ERROR = 30;
    /**
     * 级别：致命错误<br>
     * 颜色：深红
     */
    int LOG_FATAL = 40;

}
