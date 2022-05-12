package com.flab.posttoy.domain.port;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.repository.comment.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    CommentEntity insert(CommentEntity comment);
    CommentEntity update(CommentEntity comment);
    void delete(Long id);
    Optional<CommentEntity> selectById(Long id);
    List<CommentEntity> selectByPostId(Long postId);
    List<CommentEntity> selectAll();
}
