package com.flab.posttoy.web.controller;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.service.UserService;
import com.flab.posttoy.web.dto.request.RequestUserDTO;
import com.flab.posttoy.web.dto.response.ResponseUserDTO;
import com.flab.posttoy.web.mapper.WebUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WebUserMapper userMapper;

    @PostMapping("/users")
    public ResponseEntity<ResponseUserDTO> userSave(RequestUserDTO requestUser){
        User user = userService.addUser(userMapper.toUser(requestUser));
        return new ResponseEntity<>(userMapper.toResponseUser(user), HttpStatus.CREATED);
    }
}
