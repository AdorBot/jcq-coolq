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
     * CQ操作变量
     */
    protected final CoolQ CQ;

    /**
     * CQ码，赋值操作变量
     */
    protected final CQCode CC;

    /**
     * 返回应用的ApiVer、Appid，打包后将不会调用<br>
     * 本函数【禁止】处理其他任何代码，以免发生异常情况。<br>
     * 如需执行初始化代码请在 startup 事件中执行（Type=1001）。<br>
     *
     * @return 应用信息
     */
    public abstract String appInfo();

    /**
     * 构造方法 CQ变量由系统分配，继承后保留构造函数的CQ变量即可
     *
     * @param CQ CQ操作变量，由系统分配
     */
    public JcqApp(CoolQ CQ) {
        if (CQ != null)
            this.CQ = CQ;
        else
            this.CQ = CQDebug.getInstance();
        this.CC = CQCode.getInstance();
    }

    /**
     * 获取酷Q操作变量
     *
     * @return 酷Q
     */
    public final CoolQ getCoolQ() {
        return CQ;
    }

    /**
     * 获取酷Q码操作对象
     *
     * @return 酷Q码
     */
    public final CQCode getCQCode() {
        return CC;
    }

}
