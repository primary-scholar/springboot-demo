package com.mimu.simple.sd.provider.exceptions;

/**
 * author: mimu
 * date: 2019/10/22
 */
public class CustomException extends RuntimeException {

    public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
