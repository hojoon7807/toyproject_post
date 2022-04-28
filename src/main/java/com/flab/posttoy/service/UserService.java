package com.flab.posttoy.service;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.mapper.UserMapper;
import com.flab.posttoy.domain.port.UserRepository;
import com.flab.posttoy.repository.user.UserEntity;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User addUser(User user) {
        validateDuplicatedUser(user.getUsername());
        UserEntity userEntity = userRepository.insert(userMapper.toUserEntity(user));
        return userMapper.toUser(userEntity);
    }

    private void validateDuplicatedUser(String username) {
        userRepository.selectByName(username).ifPresent(u -> {
                throw new DuplicateRequestException("회원이 이미 존재합니다");
        });
    }
}
