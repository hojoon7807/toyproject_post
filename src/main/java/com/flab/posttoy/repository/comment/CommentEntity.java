package com.flab.posttoy.repository.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentEntity {
    @Builder.Default
    private Long id = Long.valueOf(0L);
    private Long userId;
    private Long postId;
    private String content;

    public void changeComment(String content) {
        if(isValidData(content)) this.content = content;
    }

    private boolean isValidData(String data){
        return data != null ? true : false;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
