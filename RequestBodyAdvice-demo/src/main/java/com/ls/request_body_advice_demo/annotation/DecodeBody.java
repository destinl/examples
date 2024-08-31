package com.ls.request_body_advice_demo.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Description: 需要对请求体进行解码
 * @Author: ls
 * @Date: 2024/8/31 22:49
 */

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface DecodeBody{

}
