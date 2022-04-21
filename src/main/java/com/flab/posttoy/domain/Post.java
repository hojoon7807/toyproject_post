package com.flab.posttoy.domain;

import lombok.Builder;
import lombok.Getter;

// 불변객체를 유지하기 위해 setter 대신 builder
@Getter
@Builder
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String content;

    public void setId(Long id) {
        this.id = id;
    }
}
