package com.flab.posttoy.service;

import com.flab.posttoy.repository.user.UserEntity;
import com.flab.posttoy.web.dto.request.CreateUserRequest;
import lombok.Getter;

@Getter
public class CreateUserCommand {
    private String username;
    private String password;

    public CreateUserCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .username(username)
                .password(password)
                .build();
    }
}
