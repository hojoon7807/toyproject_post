package com.flab.posttoy.domain;

import lombok.Builder;
import lombok.Getter;

// 불변객체를 유지하기 위해 setter 대신 builder
@Getter
@Builder
public class User {
    private Long id;
    private String username;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }
}
