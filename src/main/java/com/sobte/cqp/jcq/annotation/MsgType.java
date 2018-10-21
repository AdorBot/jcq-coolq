package com.sobte.cqp.jcq.annotation;

/**
 * Created by Sobte on 2018/6/21.<br>
 * Time: 2018/6/21 23:51<br>
 * Email: i@sobte.me<br>
 * 消息类型
 *
 * @author Sobte
 */
public enum MsgType {
    /**
     * 匹配只含普通消息的类型
     */
    Msg,
    /**
     * 匹配只含CQ码的消息
     */
    Code,
    /**
     * 匹配普通消息和CQ码都包含的消息，注：消息内必须包括普通消息和CQ码
     */
    MsgAndCode;
}
