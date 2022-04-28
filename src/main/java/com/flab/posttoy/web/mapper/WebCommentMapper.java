package com.flab.posttoy.web.mapper;

import com.flab.posttoy.entity.UpdateCommentDTO;
import com.flab.posttoy.repository.comment.CommentEntity;
import com.flab.posttoy.repository.comment.CommentMemoryRepository;
import com.flab.posttoy.web.dto.request.RequestCommentDTO;
import com.flab.posttoy.web.dto.response.ResponseCommentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebCommentMapper {

    CommentEntity toCommentDto(RequestCommentDTO requestCommentDTO);
    UpdateCommentDTO toUpdateCommentDto(RequestCommentDTO requestCommentDTO);

    ResponseCommentDTO toResponseCommentDto(CommentEntity commentEntity);
}
