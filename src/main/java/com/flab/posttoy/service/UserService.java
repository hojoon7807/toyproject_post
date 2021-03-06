package com.flab.posttoy.service;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.domain.port.UserRepository;
import com.flab.posttoy.exception.user.DuplicatedUserException;
import com.flab.posttoy.repository.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository<UserEntity,Long> userRepository;

    public User addUser(CreateUserCommand createUserCommand) {
        validateDuplicatedUser(createUserCommand.getUsername());
        UserEntity userEntity = userRepository.insert(createUserCommand.toEntity());
        User user = new User(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
        return user;
    }

    private void validateDuplicatedUser(String username) {
        userRepository.selectByUsername(username).ifPresent(u -> {
                throw new DuplicatedUserException("회원이 이미 존재합니다");
        });
    }
}
