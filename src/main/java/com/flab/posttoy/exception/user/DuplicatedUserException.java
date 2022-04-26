package com.flab.posttoy.exception.user;

import com.flab.posttoy.exception.BusinessException;
import com.flab.posttoy.exception.ErrorCodeModel;
import com.flab.posttoy.exception.UserErrorCode;

public class DuplicatedUserException extends BusinessException {

    public DuplicatedUserException(String message) {
        super(message, UserErrorCode.DUPLICATED_USERNAME);
    }
}
