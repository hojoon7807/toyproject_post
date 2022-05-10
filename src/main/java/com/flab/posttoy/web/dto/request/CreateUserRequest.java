package com.flab.posttoy.web.dto.request;

import com.flab.posttoy.service.CreateUserCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
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
