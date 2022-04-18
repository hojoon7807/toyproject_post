package com.flab.posttoy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Builder
    public User(String username, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
