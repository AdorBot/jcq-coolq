package com.sobte.cqp.jcq.annotation;

import java.lang.annotation.*;

/**
 * Created by Sobte on 2018/6/14.<br>
 * Time: 2018/6/14 18:00<br>
 * Email: i@sobte.me<br>
 * 绑定符合条件
 *
 * @author Sobte
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CQEventMapping {

    /**
     * 事件类型，详见 {@link EventType 事件类型} , 默认 {@link EventType#GroupMsg 群消息事件}
     *
     * @return 事件类型
     * @see EventType
     */
    EventType[] type() default {};

    /**
     * 匹配子类型,多个满足一个即可,多个匹配需同时满足
     *
     * @return 子类型
     */
    int[] subType() default {};

    /**
     * 匹配符合的群号,多个满足一个即可,多个匹配需同时满足
     *
     * @return 群号
     */
    long[] fromGroup() default {};

    /**
     * 匹配符合的QQ号,多个满足一个即可,多个匹配需同时满足
     *
     * @return QQ号
     */
    long[] fromQQ() default {};

    /**
     * 匹配符合的被操作QQ号,多个满足一个即可,多个匹配需同时满足
     *
     * @return 被操作QQ号
     */
    long[] beingOperateQQ() default {};

    /**
     * 匹配符合指定消息类型,多个满足一个即可,多个匹配需同时满足
     *
     * @return 消息类型
     * @see MsgType
     */
    MsgType[] msgType() default {};

    /**
     * 匹配消息前缀,多个满足一个即可,多个匹配需同时满足(完整匹配除外)<br>
     * 匹配指定功能的CQ码,键值为空即可,例：[CQ:at]<br>
     * 匹配指定功能的CQ码,指定的键值,例：[CQ:at,qq=123456]
     *
     * @return 消息前缀
     */
    String[] prefix() default {};

    /**
     * 匹配消息包含内容,多个满足一个即可,多个匹配需同时满足(完整匹配除外)<br>
     * 匹配指定功能的CQ码,键值为空即可,例：[CQ:at]<br>
     * 匹配指定功能的CQ码,指定的键值,例：[CQ:at,qq=123456]
     *
     * @return 包含内容
     */
    String[] contains() default {};

    /**
     * 完整匹配消息内容,多个满足一个即可,当满足此匹配,其他将忽略<br>
     * 匹配指定功能的CQ码,键值为空即可,例：[CQ:at]<br>
     * 匹配指定功能的CQ码,指定的键值,例：[CQ:at,qq=123456]
     *
     * @return 完整内容
     */
    String[] value() default {};

    /**
     * 匹配消息后缀,多个满足一个即可,多个匹配需同时满足(完整匹配除外)<br>
     * 匹配指定功能的CQ码,键值为空即可,例：[CQ:at]<br>
     * 匹配指定功能的CQ码,指定的键值,例：[CQ:at,qq=123456]
     *
     * @return 消息后缀
     */
    String[] suffix() default {};
}
