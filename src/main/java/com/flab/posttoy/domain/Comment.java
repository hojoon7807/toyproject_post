package com.flab.posttoy.domain;

import lombok.Getter;

@Getter
public class Comment {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;

    public void setId(Long id) {
        this.id = id;
    }
}
