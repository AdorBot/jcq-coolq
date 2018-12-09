package com.sobte.cqp.jcq.event;

import com.sobte.cqp.jcq.entity.CQDebug;
import com.sobte.cqp.jcq.entity.CoolQ;
import com.sobte.cqp.jcq.message.CQCode;

/**
 * Created by Sobte on 2018/1/29.<br>
 * Time: 19:32<br>
 * Email: i@sobte.me<br>
 * JCQ应用模板类
 */
public abstract class JcqAppAbstract extends JcqApp implements JcqListener {

    /**
     * 应用启用状态
     */
    public boolean enable = false;

    /**
     * 应用数据目录
     */
    public String appDirectory;

    /**
     * 构造方法 CQ变量由系统分配，继承后保留构造函数的CQ变量即可
     *
     * @param CQ CQ操作变量，由系统分配
     */
    public JcqAppAbstract(CoolQ CQ) {
        super(CQ);
    }

    /**
     * 用于兼容老的版本，1.3.0 将移除此构造方法<br>
     * 继承此构造方法会导致 CQ 加载的是DEBUG的 CQ 请注意！！！
     */
    @Deprecated
    protected JcqAppAbstract() {
        // TODO 用于兼容老的版本，1.3.0 将移除此构造方法
        this(CQDebug.getInstance());
    }

    @Override
    public int startup() {
        appDirectory = CQ.getAppDirectory();
        return 0;
    }

}
