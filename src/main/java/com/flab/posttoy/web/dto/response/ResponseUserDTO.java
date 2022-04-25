package com.flab.posttoy.web.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResponseUserDTO {
    @NotNull
    private Long id;
    @NotNull
    private String username;

    public ResponseUserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
