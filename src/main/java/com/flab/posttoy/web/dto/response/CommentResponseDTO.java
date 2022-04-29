package com.flab.posttoy.web.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flab.posttoy.domain.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentResponseDTO {
    private Long id;
    private Long postId;
    private Long userId;
    private String Content;

    private CommentResponseDTO(Long id, Long postId, Long userId, String content) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        Content = content;
    }

    public static CommentResponseDTO from(Comment comment) {
        return new CommentResponseDTO(comment.getId(), comment.getPostId(), comment.getUserId(), comment.getContent());
    }
}
