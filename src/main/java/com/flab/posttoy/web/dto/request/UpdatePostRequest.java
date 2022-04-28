package com.flab.posttoy.web.dto.request;

import com.flab.posttoy.domain.Post;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdatePostRequest {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    private String title;
    private String content;
}
