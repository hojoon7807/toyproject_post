package com.flab.posttoy.mapper;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.repository.comment.CommentEntity;
import com.flab.posttoy.repository.comment.CommentMemoryRepository;
import org.mapstruct.Mapper;

// spring 빈으로 등록해준다.
// Mapper가 자동으로 구현체를 만들어줌
@Mapper(componentModel = "spring")
public interface CommentMapper{
    Comment toComment(CommentEntity commentEntity);

    CommentEntity toCommentDto(Comment comment);
}
