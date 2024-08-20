package com.ls.learn.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
    enum Type{PARAM, TOKEN}
    Type limitType() default Type.PARAM;
    long lockTime() default 5;
}
