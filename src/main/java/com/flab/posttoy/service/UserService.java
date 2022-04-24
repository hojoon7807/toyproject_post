package com.flab.posttoy.service;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.dto.UserDTO;
import com.flab.posttoy.dto.mapper.CommentMapper;
import com.flab.posttoy.dto.mapper.UserMapper;
import com.flab.posttoy.repository.user.UserRepository;
import com.flab.posttoy.web.dto.request.RequestCreateUserDTO;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        validateDuplicatedUser(userDTO.getUsername());
         userRepository.insert(userMapper.toUser(userDTO));
        return null;
    }

    private void validateDuplicatedUser(String username) {
        userRepository.selectByName(username).ifPresent(u -> {
                throw new DuplicateRequestException("회원이 이미 존재합니다");
        });
    }
}
