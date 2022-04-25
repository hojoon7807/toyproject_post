package com.flab.posttoy.web.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
public class ModifyCommentReqeust {
    @NotNull
    private Long postId;
    @NotNull
    private Long userId;
    @NotEmpty
    private String Content;
}
