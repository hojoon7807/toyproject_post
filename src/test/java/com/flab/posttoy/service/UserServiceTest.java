package com.flab.posttoy.service;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.domain.port.UserRepository;
import com.flab.posttoy.exception.user.DuplicatedUserException;
import com.flab.posttoy.repository.user.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입")
    void addUser(){
        CreateUserCommand createUserCommand = new CreateUserCommand("user1", "1234");
        UserEntity userEntity = createUserCommand.toEntity();
        Long fakeId = 1L;
        ReflectionTestUtils.setField(userEntity, "id", fakeId);
        when(userRepository.insert(any())).thenReturn(userEntity);
        when(userRepository.selectByUsername(any())).thenReturn(Optional.empty());

        User user = userService.addUser(createUserCommand);

        assertAll(
                () -> assertThat(user).isInstanceOf(User.class),
                () -> assertThat(user.getId()).isEqualTo(1L),
                () -> assertThat(user.getUsername()).isEqualTo("user1"),
                () -> assertThat(user.getPassword()).isEqualTo("1234")
        );

        verify(userRepository, times(1)).insert(any(UserEntity.class));
        verify(userRepository, times(1)).selectByUsername(any(String.class));
    }

    @Test
    @DisplayName("중복된 이름의 회원이 존재하면 회원가입 실패")
    void addUser_duplicatedUser(){
        CreateUserCommand createUserCommand = new CreateUserCommand("user1", "1234");
        UserEntity userEntity = createUserCommand.toEntity();
        when(userRepository.selectByUsername(any())).thenReturn(Optional.of(userEntity));

        assertThatThrownBy(() -> userService.addUser(createUserCommand))
                .isInstanceOf(DuplicatedUserException.class)
                .hasMessage("회원이 이미 존재합니다");

        verify(userRepository, times(0)).insert(any(UserEntity.class));
        verify(userRepository, times(1)).selectByUsername(any(String.class));
    }
}
