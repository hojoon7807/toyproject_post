package com.flab.posttoy.mapper;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.entity.PostDTO;
import org.mapstruct.Mapper;

// spring 빈으로 등록해준다.
// Mapper가 자동으로 구현체를 만들어줌
@Mapper(componentModel = "spring")
public interface PostMapper{
    Post toPost(PostDTO postDTO);
    PostDTO toPostDto(Post post);
}
