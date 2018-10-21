package com.sobte.cqp.jcq.annotation;

import java.lang.annotation.*;

/**
 * Created by Sobte on 2018/8/12.<br>
 * Time: 2018/8/12 20:38<br>
 * Email: i@sobte.me<br>
 * 绑定消息内注解变量
 * TODO 待续 请勿使用
 * @author Sobte
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface MsgVariable {

    /**
     * 和 {@link #name} 效果相同
     *
     * @return 绑定名称
     */
    String value() default "";

    /**
     * 绑定消息内变量参数名称
     *
     * @return 绑定名称
     * @since 1.2.8
     */
    String name() default "";

    /**
     * 是否强制此值 必须存在，不存在即报错
     *
     * @return 是否必须
     */
    boolean required() default true;

}
