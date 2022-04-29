package com.flab.posttoy.service;

import com.flab.posttoy.repository.post.PostEntity;
import lombok.Getter;

@Getter
public class CreatePostCommand {
    private Long userId;
    private String title;
    private String content;

    private CreatePostCommand(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public static CreatePostCommand of(Long userId, String title, String content) {
        return new CreatePostCommand(userId, title, content);
    }

    public PostEntity toEntity() {
        return PostEntity.builder()
                .userId(this.userId)
                .title(this.title)
                .content(this.content)
                .build();
    }
}
