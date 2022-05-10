package com.flab.posttoy.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class SimpleMessageResponse {
    private int status;
    private String message;
}
