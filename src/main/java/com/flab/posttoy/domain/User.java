package com.flab.posttoy.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long id;
    private String username;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }
}
