package com.flab.posttoy.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String content;

    public void setId(Long id) {
        this.id = id;
    }
}
