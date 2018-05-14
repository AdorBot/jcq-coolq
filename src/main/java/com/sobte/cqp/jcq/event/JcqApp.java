package com.sobte.cqp.jcq.event;

import com.sobte.cqp.jcq.message.CQCode;
import com.sobte.cqp.jcq.entity.CoolQ;

/**
 * Created by Sobte on 2018/4/20.<br>
 * Time: 2018/4/20 22:44<br>
 * Email: i@sobte.me<br>
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
     * 返回应用的ApiVer、Appid，打包后将不会调用
     *
     * @return 应用信息
     */
    public abstract String appInfo();

}
