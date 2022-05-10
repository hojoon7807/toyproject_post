package com.flab.posttoy.web.controller;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.service.UserService;
import com.flab.posttoy.web.dto.request.CreateUserRequest;
import com.flab.posttoy.web.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> userSave(@RequestBody @Valid CreateUserRequest createUserRequest){
        log.info("json request. username: {}",createUserRequest.getUsername());
        User user = userService.addUser(createUserRequest.toCommand());
        return new ResponseEntity<>(UserResponseDTO.from(user), HttpStatus.CREATED);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<UserResponseDTO> userSaveFormRequest(@Valid CreateUserRequest createUserRequest){
        log.info("form request. username: {}",createUserRequest.getUsername());
        User user = userService.addUser(createUserRequest.toCommand());
        return new ResponseEntity<>(UserResponseDTO.from(user), HttpStatus.CREATED);
    }
}
