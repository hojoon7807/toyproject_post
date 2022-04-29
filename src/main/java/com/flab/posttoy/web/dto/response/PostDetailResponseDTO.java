package com.flab.posttoy.web.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.Post;
import com.flab.posttoy.domain.PostDetail;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDetailResponseDTO {
    private PostResponseDTO post;
    private List<CommentResponseDTO> commentList;

    private PostDetailResponseDTO(PostResponseDTO post, List<CommentResponseDTO> commentList) {
        this.post = post;
        this.commentList = commentList;
    }

    public static PostDetailResponseDTO from(PostDetail postDetail) {
        return new PostDetailResponseDTO(toPostDto(postDetail.getPost()),
                toCommentDtoList(postDetail.getCommentList()));
    }

    private static PostResponseDTO toPostDto(Post post) {
        return PostResponseDTO.from(post);
    }

    private static List<CommentResponseDTO> toCommentDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(CommentResponseDTO::from)
                .collect(Collectors.toList());
    }
}
