package com.sobte.cqp.jcq.message;

/**
 * Created by Sobte on 2018/7/13.<br>
 * Time: 2018/7/13 13:34<br>
 * Email: i@sobte.me<br>
 *
 * @author Sobte
 */
public interface Message {

    /**
     * 获取消息ID
     *
     * @return 消息ID
     */
    int getMsgId();

    /**
     * 获取消息
     *
     * @return 消息
     */
    String getMsg();

    /**
     * 获取解析消息,CQ码和普通消息
     *
     * @return 解析后消息, CQ码和普通消息
     */
    CoolQMsg getCoolQMsg();

    /**
     * 获取解析消息,CQ码(不包含普通消息)
     *
     * @return 解析后消息, CQ码(不包含普通消息)
     */
    CoolQCode getCoolQCode();

    /**
     * 当前消息对象转成文本
     *
     * @return 消息文本
     */
    String toString();

}
