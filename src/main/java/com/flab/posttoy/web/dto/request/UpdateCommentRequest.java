package com.flab.posttoy.web.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flab.posttoy.service.UpdateCommentCommand;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateCommentRequest {
    @NotNull
    private Long userId;

    private String content;

    public UpdateCommentCommand toCommand(Long id, Long postId) {
        return UpdateCommentCommand.builder()
                .id(id)
                .userId(userId)
                .postId(postId)
                .content(content)
                .build();
    }
}
