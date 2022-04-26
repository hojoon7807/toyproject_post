package com.flab.posttoy.exception;

import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCodeModel{
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
    DUPLICATED_USERNAME(HttpStatus.BAD_REQUEST, "duplicated username");

    private int status;
    private String error;

    UserErrorCode(HttpStatus status, String error) {
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
