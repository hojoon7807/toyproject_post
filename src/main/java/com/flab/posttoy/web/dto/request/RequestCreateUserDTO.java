package com.flab.posttoy.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateUserDTO {
    private String username;
    private String password;
}
