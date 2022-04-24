package com.flab.posttoy.service;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.dto.UserDTO;
import com.flab.posttoy.web.dto.request.RequestCreateUserDTO;

import java.util.List;

public interface IUserService {
    UserDTO addUser(UserDTO UserDTO);


}
