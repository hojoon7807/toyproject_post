package com.flab.posttoy.service;

import com.flab.posttoy.domain.Comment;

public interface ICommentService {
    Comment addComment(Comment comment);

    Comment modifyComment(Comment comment, Long id);

    void removeComment(Long id);
}
