package org.meowy.cqp.jcq.annotation.common;

import org.meowy.cqp.jcq.annotation.CQEventMapping;
import org.meowy.cqp.jcq.annotation.EventType;

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
