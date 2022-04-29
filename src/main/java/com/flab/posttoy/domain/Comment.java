package com.flab.posttoy.domain;

import lombok.Builder;
import lombok.Getter;

// 불변객체를 유지하기 위해 setter 대신 builder
@Getter
public class Comment {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;

    @Builder
    public Comment(Long id, Long userId, Long postId, String content) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }
}
