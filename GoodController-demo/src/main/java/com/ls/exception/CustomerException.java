package com.ls.exception;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/12 21:08
 */
public class CustomerException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public CustomerException(String message) {
        super(message);
        this.code = 500;
    }

    public CustomerException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
