package com.flab.posttoy.dto;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostWithCommentDTO {
    private Post post;
    private List<Comment> commentList;

    public PostWithCommentDTO(Post post, List<Comment> commentList) {
        this.post = post;
        this.commentList = commentList;
    }
}
