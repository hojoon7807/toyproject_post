package com.flab.posttoy.web.exception.advice;

import com.flab.posttoy.exception.comment.CommentNotFoundException;
import com.flab.posttoy.exception.user.DuplicatedUserException;
import com.flab.posttoy.exception.user.UserNotFoundException;
import com.flab.posttoy.web.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotFoundException(CommentNotFoundException e){
        return new ResponseEntity<>(ErrorResponse.of(e.getErrorCode(),e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("e.getModel(). = " + e.getModel());
        return new ResponseEntity<>(ErrorResponse.of(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public ResponseEntity<ErrorResponse> hadleDuplicatedUserException(DuplicatedUserException e){
        return new ResponseEntity<>(ErrorResponse.of(e.getErrorCode(),e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getErrorCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
