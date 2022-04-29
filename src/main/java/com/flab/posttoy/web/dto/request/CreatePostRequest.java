package com.flab.posttoy.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreatePostRequest {
    @NotNull
    private Long userId;
    @NotBlank
    private String title;
    @NotNull
    private String content;
}
