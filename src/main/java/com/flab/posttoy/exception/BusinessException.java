package com.flab.posttoy.exception;

public class BusinessException extends RuntimeException{
    private ErrorCodeModel errorCode;

    public BusinessException(String message, ErrorCodeModel errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public ErrorCodeModel getErrorCode() {
        return errorCode;
    }
}
