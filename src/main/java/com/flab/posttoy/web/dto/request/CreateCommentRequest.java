package com.flab.posttoy.web.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flab.posttoy.service.CreateCommentCommand;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateCommentRequest {
    @NotNull
    private Long postId;

    @NotNull
    private Long userId;

    @NotEmpty
    private String content;

    public CreateCommentCommand toCommand(){
        return new CreateCommentCommand(userId, postId, content);
    }
}
