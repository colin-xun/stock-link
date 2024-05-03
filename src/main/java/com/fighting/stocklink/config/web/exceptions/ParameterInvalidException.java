package com.fighting.stocklink.config.web.exceptions;

/**
 * @author： colin_xun@163.com
 * @create: 2019-01-27 20:06
 * @description: 参数不符合规范
 */
public class ParameterInvalidException extends RuntimeException {

    public ParameterInvalidException() {
    }

    public ParameterInvalidException(String message) {
        super(message);
    }

    public ParameterInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterInvalidException(Throwable cause) {
        super(cause);
    }

    public ParameterInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
