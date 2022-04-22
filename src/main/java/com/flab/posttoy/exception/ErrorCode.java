package com.flab.posttoy.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements ErrorCodeModel{
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "invalid input value"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "invalid type value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "method not allowed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");

    private int status;
    private String error;

    ErrorCode(HttpStatus status, String error) {
        this.status = status.value();
        this.error = error;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getError() {
        return error;
    }
}
