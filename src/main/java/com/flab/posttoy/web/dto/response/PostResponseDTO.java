package com.flab.posttoy.web.dto.response;

import com.flab.posttoy.domain.Post;
import lombok.Getter;

@Getter
public class PostResponseDTO {
    private Long id;
    private Long userId;
    private String title;
    private String content;

    private PostResponseDTO(Long id, Long userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public static PostResponseDTO from(Post post) {
        return new PostResponseDTO(post.getId(), post.getUserId(), post.getTitle(), post.getContent());
    }
}
