package com.sobte.cqp.jcq.event;

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
    public static boolean enable = false;

    /**
     * 应用数据目录
     */
    public static String appDirectory = CQ.getAppDirectory();

}
