package com.flab.posttoy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 불변객체를 유지하기 위해 setter 대신 builder
@Getter
public class User {
    private Long id;
    private String username;
    private String password;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
