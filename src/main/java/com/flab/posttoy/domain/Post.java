package com.flab.posttoy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private Long id;
    private String title;
    private String content;
    private Long userId;
}
