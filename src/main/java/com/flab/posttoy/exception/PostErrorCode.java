package com.flab.posttoy.exception;

import org.springframework.http.HttpStatus;

public enum PostErrorCode implements ErrorCodeModel{
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found");

    private int status;
    private String error;

    PostErrorCode(HttpStatus status, String error) {
        this.status = status.value();
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
