package com.flab.posttoy.repository.comment;

import com.flab.posttoy.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment insert(Comment comment);
    Comment update(Comment comment);
    void delete(Long id);
    Optional<Comment> selectById(Long id);
    List<Comment> selectByPostId(Long postId);
    List<Comment> selectAll();

    void clearStore();

}
