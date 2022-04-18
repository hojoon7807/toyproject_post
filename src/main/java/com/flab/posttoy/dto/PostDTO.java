package com.flab.posttoy.dto;

import com.flab.posttoy.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

public class PostDTO {

    @Getter
    @ToString
    public static class RequestPost {
        private String title;
        private String content;
        private Long userId;

        public Post toEntity(){
            return Post.builder()
                    .title(this.title)
                    .content(this.content)
                    .userId(this.userId)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ResponsePost {
        private Long id;
        private String title;
        private String content;
        private Long userId;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public static ResponsePost from(Post post){
            return ResponsePost.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .userId(post.getUserId())
                    .createdAt(post.getCreatedAt())
                    .modifiedAt(post.getModifiedAt())
                    .build();
        }
    }
}
