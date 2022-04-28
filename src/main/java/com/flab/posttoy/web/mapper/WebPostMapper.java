package com.flab.posttoy.web.mapper;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.Post;
import com.flab.posttoy.web.dto.request.RequestPostDTO;
import com.flab.posttoy.web.dto.response.PostResponseDTO;
import com.flab.posttoy.web.dto.response.PostDetailResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebPostMapper {
    PostDetailResponseDTO toPostDetail(Post post, Comment comment);

    Post toPost(RequestPostDTO requestPostDTO);

    PostResponseDTO toResponsePost(Post post);
}
