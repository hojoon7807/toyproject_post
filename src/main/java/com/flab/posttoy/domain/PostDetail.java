package com.flab.posttoy.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class PostDetail {
    private Post post;
    private List<Comment> commentList;

    private PostDetail(Post post, List<Comment> commentList) {
        this.post = post;
        this.commentList = commentList;
    }

    public static PostDetail of(Post post, List<Comment> commentList) {
        return new PostDetail(post, commentList);
    }
}
