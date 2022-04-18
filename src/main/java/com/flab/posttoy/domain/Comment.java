package com.flab.posttoy.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class Comment {
    private Long id;
    private String content;
    private Long postId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void changeComment(String content) {
        this.content = content;
    }

    @Builder
    public Comment(String content, Long postId, Long userId) {
        this.content = content;
        this.postId = postId;
        this.userId = userId;
    }
}
