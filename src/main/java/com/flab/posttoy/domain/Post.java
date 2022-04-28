package com.flab.posttoy.domain;

import com.flab.posttoy.repository.post.PostEntity;
import lombok.Builder;
import lombok.Getter;


// 불변객체를 유지하기 위해 setter 대신 builder
@Getter
@Builder
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String content;

    public static Post from(PostEntity postEntity) {
        return Post.builder()
                .id(postEntity.getId())
                .userId(postEntity.getUserId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .build();
    }
}
