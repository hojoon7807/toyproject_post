package com.flab.posttoy.exception;

import org.springframework.http.HttpStatus;

public enum CommentErrorCode implements ErrorCodeModel{
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "comment not found");

    private int status;
    private String error;

    CommentErrorCode(HttpStatus status, String error) {
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
