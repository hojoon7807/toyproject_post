package com.flab.posttoy.repository.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    private Long id;
    private String username;
    private String password;
}
