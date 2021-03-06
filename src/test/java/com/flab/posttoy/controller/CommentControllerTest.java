package com.flab.posttoy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.exception.comment.CommentNotFoundException;
import com.flab.posttoy.exception.post.PostNotFoundException;
import com.flab.posttoy.exception.user.UserNotFoundException;
import com.flab.posttoy.service.CommentService;
import com.flab.posttoy.web.controller.CommentController;
import com.flab.posttoy.web.dto.request.CreateCommentRequest;
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
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentService commentService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void prepare(){
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).addFilter(new CharacterEncodingFilter("UTF-8", true)).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    @DisplayName("?????? ?????? ?????? ????????? ??????????????? ????????????, ?????? ????????? ????????????")
    void commentSave() throws Exception {

        final Long ID = 1L;
        final Long POST_ID = 1L;
        final Long USER_ID = 1L;
        final String CONTENT = "new comment";
        CreateCommentRequest createCommentRequest = new CreateCommentRequest(POST_ID, USER_ID, CONTENT);
        Comment comment = new Comment(ID, USER_ID, POST_ID, CONTENT);

        when(commentService.addComment(any())).thenReturn(comment);

        ResultActions resultActions = mockMvc.perform(post("/posts/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCommentRequest))
        );

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("id",ID).exists())
                .andExpect(jsonPath("post_id",POST_ID).exists())
                .andExpect(jsonPath("user_id",USER_ID).exists())
                .andExpect(jsonPath("content",CONTENT).exists());

        verify(commentService, times(1)).addComment(any());
    }

    @Test
    @DisplayName("?????? ?????? ?????? ????????? ???????????? ?????? ?????????, ???????????? ????????? ????????????")
    void commentSave_errorResponse_methodArgumentNotValidException() throws Exception {
        final String EXCEPTION_FIELD = "postId";
        CreateCommentRequest createCommentRequest = new CreateCommentRequest(null, 1L, "new comment");

        ResultActions resultActions = mockMvc.perform(post("/posts/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCommentRequest))
        );

        resultActions.andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(MethodArgumentNotValidException.class))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("???????????? ?????? ????????????"))
                .andExpect(jsonPath("$.error").value("invalid input value"))
                .andExpect(jsonPath("$.errors.[0].field").value(EXCEPTION_FIELD));
        verify(commentService, never()).addComment(any());
    }

    @Test
    @DisplayName("id ???????????? ???????????? ?????? ????????????, NotFound ???????????? ????????? ????????????")
    void commentSave_errorResponse_notFound() throws Exception {
        final Long USER_ID = 1L;
        CreateCommentRequest createCommentRequest = new CreateCommentRequest(1L, USER_ID, "comment");
        when(commentService.addComment(any())).thenThrow(new UserNotFoundException(USER_ID + ":???????????? ????????? ???????????? ????????????"));

        ResultActions resultActions = mockMvc.perform(post("/posts/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCommentRequest))
        );

        resultActions.andExpect(status().isNotFound())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(UserNotFoundException.class))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(USER_ID + ":???????????? ????????? ???????????? ????????????"))
                .andExpect(jsonPath("$.error").value("user not found"));

        verify(commentService, times(1)).addComment(any());
    }

    @Test
    @DisplayName("id??? ???????????? comment??? ????????? not found ???????????? ????????? ????????????")
    void commentRemove_erroResponse_notFound() throws Exception{
        final Long ID = 1L;
        doThrow(new CommentNotFoundException(ID + ":???????????? ????????? ???????????? ????????????")).when(commentService).removeComment(ID);
        mockMvc.perform(delete("/posts/1/comments/{commentId}", ID))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(CommentNotFoundException.class))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(ID + ":???????????? ????????? ???????????? ????????????"))
                .andExpect(jsonPath("$.error").value("comment not found"));
    }

    @Test
    @DisplayName("id??? ???????????? comment??? ????????? ??????????????? comment??? ????????????")
    void commentRemove() throws Exception{
        final Long ID = 1L;
        mockMvc.perform(delete("/posts/1/comments/{commentId}", ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value(ID+": ????????? ?????????????????????."));

        verify(commentService,times(1)).removeComment(ID);
    }
}
