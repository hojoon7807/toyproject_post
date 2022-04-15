package com.flab.posttoy.dto;

import com.flab.posttoy.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SignUpDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
