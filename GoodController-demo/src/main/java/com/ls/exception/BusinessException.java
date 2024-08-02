package com.ls.exception;

/**
 * @Description:我们希望无论系统发生什么异常，Http 的状态码都要是 200 ，尽可能由业务来区分系统的异常
 * @Author: ls
 * @Date: 2024/8/2 23:45
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}