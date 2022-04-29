package com.flab.posttoy.repository.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentEntity {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;

    @Builder
    private CommentEntity(Long id, Long userId, Long postId, String content) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public void changeComment(String content) {
        if(isValidData(content)) this.content = content;
    }

    private boolean isValidData(String data){
        return data == null || data.isBlank() ? false : true;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
