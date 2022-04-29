package com.flab.posttoy.web.controller;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.service.UserService;
import com.flab.posttoy.web.dto.request.CreateUserRequest;
import com.flab.posttoy.web.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> userSave(@RequestBody @Valid CreateUserRequest createUserRequest){
        User user = userService.addUser(createUserRequest.toCommand());
        return new ResponseEntity<>(UserResponseDTO.from(user), HttpStatus.CREATED);
    }
}
