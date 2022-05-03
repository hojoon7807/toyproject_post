package com.flab.posttoy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.posttoy.domain.User;
import com.flab.posttoy.service.CreateUserCommand;
import com.flab.posttoy.service.UserService;
import com.flab.posttoy.web.controller.UserController;
import com.flab.posttoy.web.dto.request.CreateUserRequest;
import com.flab.posttoy.web.dto.response.UserResponseDTO;
import com.flab.posttoy.web.exception.advice.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void userSave() throws Exception {
        CreateUserRequest createUserRequest = new CreateUserRequest("user1", "1234");
        User user1 = new User(1L, "user1", "1234");
        UserResponseDTO userResponse = UserResponseDTO.from(user1);
        when(userService.addUser(any(CreateUserCommand.class))).thenReturn(user1);

        ResultActions resultActions = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequest))
        );

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("id",userResponse.getId()).exists())
                .andExpect(jsonPath("username", userResponse.getUsername()).exists());

        verify(userService, times(1)).addUser(any(CreateUserCommand.class));
    }

    @Test
    @DisplayName("요청받은 데이터에 username 값이 없으면 회원 가입 실패")
    void userSave_usernameArgumentNotValid() throws Exception {
        CreateUserRequest invalidCreateUserRequest = new CreateUserRequest(null, "1234");

        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidCreateUserRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(MethodArgumentNotValidException.class))
                .andExpect(result -> assertThat(result.getResponse().getContentAsString(StandardCharsets.UTF_8)).contains("username"))
                .andExpect(result -> assertThat(result.getResponse().getContentAsString(StandardCharsets.UTF_8)).doesNotContain("password"));
    }

    @Test
    @DisplayName("요청받은 데이터에 password 값이 없으면 회원 가입 실패")
    void userSave_passwordArgumentNotValid() throws Exception {
        CreateUserRequest invalidCreateUserRequest = new CreateUserRequest("hojoon", null);

        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidCreateUserRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(MethodArgumentNotValidException.class))
                .andExpect(result -> assertThat(result.getResponse().getContentAsString(StandardCharsets.UTF_8)).contains("password"))
                .andExpect(result -> assertThat(result.getResponse().getContentAsString(StandardCharsets.UTF_8)).doesNotContain("username"));
    }
}
