package com.example.webmvc.exception;

/**
 * Created by Administrator on 2017/1/18.
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
