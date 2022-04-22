package com.flab.posttoy.web.exception;

import com.flab.posttoy.exception.ErrorCode;
import com.flab.posttoy.exception.ErrorCodeModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private List<FieldError> errors;

    private ErrorResponse(ErrorCodeModel errorCode, String message) {
        this.status = errorCode.getStatus();
        this.error = errorCode.getError();
        this.message = message;
        this.errors = new ArrayList<>();
    }
    private ErrorResponse(ErrorCodeModel errorCode, String message, List<FieldError> errors) {
        this.status = errorCode.getStatus();
        this.error = errorCode.getError();
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse of(ErrorCodeModel errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }

    public static ErrorResponse of(MethodArgumentNotValidException e) {
        return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, "비어있는 값이 있습니다", FieldError.of(e.getBindingResult()));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
