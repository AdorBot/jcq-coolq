package com.sobte.cqp.jcq.entity;

/**
 * Created by Sobte on 2018/1/31.
 * Time: 23:38
 * Email: i@sobte.me
 * 信息拦截
 */
public interface IMsg {

    /**
     * 将此消息继续传递给其他应用
     */
    int MSG_IGNORE = 0;
    /**
     * 拦截此条消息，不再传递给其他应用 //注意：应用优先级设置为"最高"(10000)时，不得使用本返回值
     */
    int MSG_INTERCEPT = 1;

}
