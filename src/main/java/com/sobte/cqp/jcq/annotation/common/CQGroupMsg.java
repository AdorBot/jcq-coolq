package com.sobte.cqp.jcq.annotation.common;

import com.sobte.cqp.jcq.annotation.CQEventMapping;
import com.sobte.cqp.jcq.annotation.EventType;

import java.lang.annotation.*;

/**
 * Created by Sobte on 2018/10/21.<br>
 * Time: 2018/10/21 21:43<br>
 * Email: i@sobte.me<br>
 * 绑定群消息事件
 *
 * @author Sobte
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@CQEventMapping(type = EventType.GroupMsg)
public @interface CQGroupMsg {
}
