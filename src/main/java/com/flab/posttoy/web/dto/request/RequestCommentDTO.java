package com.flab.posttoy.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestCommentDTO {

    @JsonProperty("post_id")
    @NotNull
    private Long postId;

    @JsonProperty("user_id")
    @NotNull
    private Long userId;

    @NotEmpty
    private String content;
}
