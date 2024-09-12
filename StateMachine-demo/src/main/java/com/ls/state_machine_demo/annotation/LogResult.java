package com.ls.state_machine_demo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description: 使用aop对监听事件切面，把业务执行结果封装到状态机的变量中
 * @Author: ls
 * @Date: 2024/9/12 22:50
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogResult {
    /**
     *执行的业务key
     *
     * @return String
     */
    String key();
}
