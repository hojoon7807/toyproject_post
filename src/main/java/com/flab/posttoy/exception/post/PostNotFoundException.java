package com.flab.posttoy.exception.post;

import com.flab.posttoy.exception.BusinessException;

public class PostNotFoundException extends BusinessException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
