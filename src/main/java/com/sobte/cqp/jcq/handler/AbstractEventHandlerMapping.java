package com.sobte.cqp.jcq.handler;

import com.sobte.cqp.jcq.EventHandlerInterceptor;
import com.sobte.cqp.jcq.EventHandlerMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sobte on 2018/6/22.<br>
 * Time: 2018/6/22 12:53<br>
 * Email: i@sobte.me<br>
 * TODO 待续 请勿使用
 *
 * @author Sobte
 */
@Deprecated
public abstract class AbstractEventHandlerMapping implements EventHandlerMapping {

    private final List<EventHandlerInterceptor> adaptedInterceptors = new ArrayList<EventHandlerInterceptor>();

}
