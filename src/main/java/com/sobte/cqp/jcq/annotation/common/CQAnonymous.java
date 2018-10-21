package com.sobte.cqp.jcq.annotation.common;

import com.sobte.cqp.jcq.annotation.CQEventMapping;
import com.sobte.cqp.jcq.annotation.EventType;
import com.sobte.cqp.jcq.entity.ISystem;

import java.lang.annotation.*;

/**
 * Created by Sobte on 2018/8/7.<br>
 * Time: 2018/8/7 20:24<br>
 * Email: i@sobte.me<br>
 * 绑定匿名消息的群事件
 *
 * @author Sobte
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@CQEventMapping(fromQQ = ISystem.System_QQID_Anonymous, type = EventType.GroupMsg)
public @interface CQAnonymous {



}
