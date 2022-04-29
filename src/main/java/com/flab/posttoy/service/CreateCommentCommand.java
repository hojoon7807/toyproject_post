package com.flab.posttoy.service;

import com.flab.posttoy.repository.comment.CommentEntity;
import lombok.Getter;

@Getter
public class CreateCommentCommand {
    private Long userId;
    private Long postId;
    private String content;

    public CreateCommentCommand(Long userId, Long postId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public CommentEntity toEntity(){
        return CommentEntity.builder()
                .userId(userId)
                .postId(postId)
                .content(content)
                .build();
    }
}
