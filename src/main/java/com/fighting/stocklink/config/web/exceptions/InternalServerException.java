package com.fighting.stocklink.config.web.exceptions;

/**
 * @author： colin_xun@163.com
 * @create: 2019-01-27 20:09
 * @description: 系统内部异常
 */
public class InternalServerException extends RuntimeException {

    public InternalServerException() {
    }

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(Throwable cause) {
        super(cause);
    }

    public InternalServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
