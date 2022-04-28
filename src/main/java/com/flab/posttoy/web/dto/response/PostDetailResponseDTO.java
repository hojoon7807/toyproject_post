package com.flab.posttoy.web.dto.response;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDetailResponseDTO {
    private Post post;
    private List<Comment> commentList;
}
