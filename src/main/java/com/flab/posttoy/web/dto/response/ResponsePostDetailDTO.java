package com.flab.posttoy.web.dto.response;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.Post;
import com.flab.posttoy.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePostDetailDTO {
    private Post post;
    private List<Comment> commentList;
}
