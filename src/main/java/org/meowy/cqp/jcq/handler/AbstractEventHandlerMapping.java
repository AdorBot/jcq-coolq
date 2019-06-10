package org.meowy.cqp.jcq.handler;

import org.meowy.cqp.jcq.EventHandlerInterceptor;
import org.meowy.cqp.jcq.EventHandlerMapping;

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
