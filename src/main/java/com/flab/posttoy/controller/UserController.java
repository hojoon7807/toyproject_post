package com.flab.posttoy.controller;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.dto.SignUpDTO;
import com.flab.posttoy.service.UserService;
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

    // ExceptionHandler 추가 예정
    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody @Valid SignUpDTO signUpDTO){
        User user = signUpDTO.toEntity();
        return ResponseEntity.ok().body(userService.signUp(signUpDTO.toEntity()));
    }


}
