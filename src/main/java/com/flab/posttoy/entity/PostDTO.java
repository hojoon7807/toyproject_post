package com.flab.posttoy.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private Long userId;
    private String title;
    private String content;
}
