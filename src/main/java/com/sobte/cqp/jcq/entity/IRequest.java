package com.sobte.cqp.jcq.entity;

/**
 * Created by Sobte on 2018/1/31.
 * Time: 23:22
 * Email: i@sobte.me
 * 请求判断
 */
public interface IRequest {

    /**
     * 通过
     */
    int REQUEST_ADOPT = 1;
    /**
     * 拒绝
     */
    int REQUEST_REFUSE = 2;
    /**
     * 群添加
     */
    int REQUEST_GROUP_ADD = 1;
    /**
     * 群邀请
     */
    int REQUEST_GROUP_INVITE = 2;

}
