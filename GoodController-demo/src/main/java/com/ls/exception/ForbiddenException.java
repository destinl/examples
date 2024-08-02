package com.ls.exception;

/**
 * @Description://自定义异常
 * @Author: ls
 * @Date: 2024/8/2 23:44
 */

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
