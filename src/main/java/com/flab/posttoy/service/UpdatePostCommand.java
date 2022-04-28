package com.flab.posttoy.service;

import lombok.Getter;

@Getter
public class UpdatePostCommand {
    private Long userId;
    private String title;
    private String content;

    private UpdatePostCommand(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public static UpdatePostCommand of(Long userId, String title, String content) {
        return new UpdatePostCommand(userId, title, content);
    }
}
