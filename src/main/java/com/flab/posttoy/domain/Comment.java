package com.flab.posttoy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private Long id;
    private String content;
    private Long postId;
    private Long userId;
}
