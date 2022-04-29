package com.flab.posttoy.web.dto.request;

import com.flab.posttoy.service.CreateUserCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class CreateUserRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public CreateUserCommand toCommand(){
        return new CreateUserCommand(username, password);
    }
}
