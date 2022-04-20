package com.flab.posttoy.dto.mapper;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.dto.CommentDTO;
import org.mapstruct.Mapper;

// spring 빈으로 등록해준다.
// Mapper가 자동으로 구현체를 만들어줌
@Mapper(componentModel = "spring")
public interface CommentMapper extends GenericMapper <CommentDTO, Comment>{
}
