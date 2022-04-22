package com.flab.posttoy.exception.user;

import com.flab.posttoy.exception.BusinessException;
import com.flab.posttoy.exception.ErrorCode;
import com.flab.posttoy.exception.UserErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String message) {
        super(message, UserErrorCode.USER_NOT_FOUND);
    }
}
