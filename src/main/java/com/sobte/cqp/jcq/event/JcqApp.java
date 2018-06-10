package com.sobte.cqp.jcq.event;

import com.sobte.cqp.jcq.message.CQCode;
import com.sobte.cqp.jcq.entity.CoolQ;

/**
 * Created by Sobte on 2018/4/20.<br>
 * Time: 2018/4/20 22:44<br>
 * Email: i@sobte.me<br>
 * JCQ应用必须类
 *
 * @author Sobte
 */
public abstract class JcqApp {

    /**
     * CQ操作变量
     */
    public static CoolQ CQ;

    /**
     * CQ码，赋值操作变量
     */
    public final static CQCode CC = new CQCode();

    /**
     * 返回应用的ApiVer、Appid，打包后将不会调用<br>
     * 本函数【禁止】处理其他任何代码，以免发生异常情况。<br>
     * 如需执行初始化代码请在 startup 事件中执行（Type=1001）。<br>
     *
     * @return 应用信息
     */
    public abstract String appInfo();

}
