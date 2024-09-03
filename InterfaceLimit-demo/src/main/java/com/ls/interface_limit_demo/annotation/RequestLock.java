package com.ls.interface_limit_demo.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 请求防抖锁，用于防止前端重复提交导致的错误
 * @Author: ls
 * @Date: 2024/9/3 22:37
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestLock {
    /**
     * redis锁前缀
     *
     * @return 默认为空，但不可为空
     */
    String prefix() default "";
    /**
     * redis锁过期时间
     *
     * @return 默认2秒
     */
    int expire() default 2;
    /**
     * redis锁过期时间单位
     *
     * @return 默认单位为秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
    /**
     * redis  key分隔符
     *
     * @return 分隔符
     */
    String delimiter() default "&";
}

