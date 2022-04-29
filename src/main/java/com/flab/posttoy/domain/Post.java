package com.flab.posttoy.domain;

import com.flab.posttoy.repository.post.PostEntity;
import lombok.Builder;
import lombok.Getter;


// 불변객체를 유지하기 위해 setter 대신 builder
@Getter
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String content;

    @Builder
    public Post(Long id, Long userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
