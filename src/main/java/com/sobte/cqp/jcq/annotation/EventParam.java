package com.sobte.cqp.jcq.annotation;

import java.lang.annotation.*;

/**
 * Created by Sobte on 2018/8/12.<br>
 * Time: 2018/8/12 20:06<br>
 * Email: i@sobte.me<br>
 * 参数绑定
 *
 * @author Sobte
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventParam {

    /**
     * 和 {@link #name} 效果相同
     *
     * @return 绑定名称
     */
    String value() default "";

    /**
     * 绑定参数名称
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

    /**
     * 默认值
     *
     * @return 值
     */
    String defaultValue() default "";

}
