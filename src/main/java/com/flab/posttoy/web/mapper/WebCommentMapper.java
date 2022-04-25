package com.flab.posttoy.web.mapper;

import com.flab.posttoy.entity.CommentDTO;
import com.flab.posttoy.entity.UpdateCommentDTO;
import com.flab.posttoy.web.dto.request.RequestCommentDTO;
import com.flab.posttoy.web.dto.response.ResponseCommentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebCommentMapper {
    CommentDTO toCommentDto(RequestCommentDTO requestCommentDTO);
    UpdateCommentDTO toUpdateCommentDto(RequestCommentDTO requestCommentDTO);

    ResponseCommentDTO toResponseCommentDto(CommentDTO commentDTO);
}
