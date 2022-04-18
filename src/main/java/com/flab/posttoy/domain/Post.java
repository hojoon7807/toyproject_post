package com.flab.posttoy.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Post {
    private Long id = 0L;
    private String title;
    private String content;
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

    public void changePost(String title, String content) {
        if(isValidData(title)) this.title = title;
        if(isValidData(content)) this.content = content;
    }

    private boolean isValidData(String data){
        return data == null ? true : false;
    }

    @Builder
    public Post(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
