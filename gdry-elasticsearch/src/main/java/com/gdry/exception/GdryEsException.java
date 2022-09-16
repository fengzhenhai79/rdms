package com.gdry.exception;

/**
 * GdryEs异常类
 * <p>
 * Copyright © 2021 xpc1024 All Rights Reserved
 **/
public class GdryEsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GdryEsException(String message) {
        super(message);
    }

    public GdryEsException(Throwable throwable) {
        super(throwable);
    }

    public GdryEsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
