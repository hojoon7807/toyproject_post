package com.flab.posttoy.service;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateCommentCommand {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;

    @Builder
    public UpdateCommentCommand(Long id, Long userId, Long postId, String content) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }
}
