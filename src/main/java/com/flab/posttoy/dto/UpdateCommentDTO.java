package com.flab.posttoy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentDTO {
    private Long userId;
    private Long postId;
    private String content;
}
