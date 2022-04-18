package com.flab.posttoy.exception;

public class DuplicatedUserException extends RuntimeException{
    public DuplicatedUserException(String message) {
        super(message);
    }
}
