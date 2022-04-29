package com.flab.posttoy.web.dto.response;

import com.flab.posttoy.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    private Long id;
    private String username;

    private UserResponseDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername());
    }
}
