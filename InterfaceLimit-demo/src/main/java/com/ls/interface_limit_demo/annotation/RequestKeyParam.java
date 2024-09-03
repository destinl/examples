package com.ls.interface_limit_demo.annotation;

import java.lang.annotation.*;

/**
 * @Description: 加上这个注解可以将参数设置为key
 * @Author: ls
 * @Date: 2024/9/3 22:30
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestKeyParam {
}
