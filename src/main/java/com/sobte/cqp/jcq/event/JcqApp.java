package com.sobte.cqp.jcq.event;

import com.sobte.cqp.jcq.entity.CQDebug;
import com.sobte.cqp.jcq.entity.CoolQ;
import com.sobte.cqp.jcq.message.CQCode;

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
     * CQ操作变量 隔类不建议直接调用 请使用 {@link #getCQCode()} 此变量未来可能改动访问方式<br>
     * 类中访问更新不受影响
     */
    @Deprecated
    public static final CoolQ CQ = new CQDebug();

    /**
     * CQ码，赋值操作变量 隔类不建议直接调用 请使用 {@link #getCQCode()} 此变量未来可能改动访问方式<br>
     * 类中访问更新不受影响
     */
    @Deprecated
    protected static final CQCode CC = new CQCode();

    /**
     * 返回应用的ApiVer、Appid，打包后将不会调用<br>
     * 本函数【禁止】处理其他任何代码，以免发生异常情况。<br>
     * 如需执行初始化代码请在 startup 事件中执行（Type=1001）。<br>
     *
     * @return 应用信息
     */
    public abstract String appInfo();

    /**
     * 获取CoolQ操作对象
     *
     * @return 酷Q操作对象
     */
    public static CoolQ getCoolQ() {
        return CQ;
    }

    /**
     * 获取CQCode操作对象，用于操作
     *
     * @return 酷Q操作对象
     */
    public static CQCode getCQCode() {
        return CC;
    }

}
