package com.flab.posttoy.exception.comment;

import com.flab.posttoy.exception.BusinessException;
import com.flab.posttoy.exception.CommentErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CommentNotFoundException extends BusinessException {
    public CommentNotFoundException(String message) {
        super(message, CommentErrorCode.COMMENT_NOT_FOUND);
    }
}
