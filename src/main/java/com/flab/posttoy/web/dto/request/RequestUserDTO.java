package com.flab.posttoy.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RequestUserDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
