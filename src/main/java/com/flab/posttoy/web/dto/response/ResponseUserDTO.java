package com.flab.posttoy.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResponseUserDTO {
    private Long id;
    private String username;

    public ResponseUserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
