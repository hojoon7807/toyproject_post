package com.flab.posttoy.web.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResponseCommentDTO {

    @NotNull
    private Long id;

    @JsonProperty("post_id")
    @NotNull
    private Long postId;

    @JsonProperty("user_id")
    @NotNull
    private Long userId;

    @NotEmpty
    private String Content;
}
