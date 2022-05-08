package com.flab.posttoy.service;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.port.CommentRepository;
import com.flab.posttoy.domain.port.PostRepository;
import com.flab.posttoy.domain.port.UserRepository;
import com.flab.posttoy.exception.comment.CommentNotFoundException;
import com.flab.posttoy.exception.post.PostNotFoundException;
import com.flab.posttoy.exception.user.UserNotFoundException;
import com.flab.posttoy.repository.comment.CommentEntity;
import com.flab.posttoy.repository.post.PostEntity;
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
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("요청받은 댓글 추가 정보를 정상적으로 저장하고 Comment 객체를 반환한다")
    void addComment(){
        final Long ID = 1L;
        final Long USER_ID = 1L;
        final Long POST_ID = 1L;
        final String CONTENT = "content";

        PostEntity postEntity = PostEntity.builder().id(POST_ID).build();
        CreateCommentCommand createCommentCommand = new CreateCommentCommand(USER_ID, POST_ID, CONTENT);
        CommentEntity commentEntity = createCommentCommand.toEntity();
        ReflectionTestUtils.setField(commentEntity,"id",ID);

        when(postRepository.selectById(any(Long.class))).thenReturn(Optional.of(postEntity));
        when(commentRepository.insert(any(CommentEntity.class))).thenReturn(commentEntity);

        Comment comment = commentService.addComment(createCommentCommand);

        assertAll(
                () -> assertThat(comment).isInstanceOf(Comment.class),
                () -> assertThat(comment.getId()).isEqualTo(ID),
                () -> assertThat(comment.getPostId()).isEqualTo(POST_ID),
                () -> assertThat(comment.getUserId()).isEqualTo(USER_ID),
                () -> assertThat(comment.getContent()).isEqualTo(CONTENT)
        );

        verify(postRepository, times(1)).selectById(any(Long.class));
        verify(commentRepository, times(1)).insert(any(CommentEntity.class));
    }

    @Test
    @DisplayName("댓글 추가 요청을 받았을 때 댓글이 작성되는 포스트가 존재하지 않으면 NotFound 예외가 발생한다")
    void addComment_PostNotFound(){
        final Long USER_ID = 1L;
        final Long POST_ID = 1L;
        final String CONTENT = "content";

        CreateCommentCommand createCommentCommand = new CreateCommentCommand(USER_ID, POST_ID, CONTENT);

        when(postRepository.selectById(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.addComment(createCommentCommand))
                .isInstanceOf(PostNotFoundException.class)
                .hasMessage(POST_ID + ":해당하는 포스트가 존재하지 않습니다");

        verify(postRepository, times(1)).selectById(any(Long.class));
        verify(commentRepository, never()).insert(any(CommentEntity.class));
    }

    @Test
    @DisplayName("요청받은 id에 해당하는 댓글을 정상적으로 삭제한다")
    void removeComment(){
        final Long ID = 1L;

        CommentEntity comment = CommentEntity.builder()
                .id(ID)
                .postId(1L)
                .userId(1L)
                .content("content").build();

        when(commentRepository.selectById(any(Long.class))).thenReturn(Optional.of(comment));

        commentService.removeComment(ID);

        verify(commentRepository, times(1)).selectById(any(Long.class));
        verify(commentRepository, times(1)).delete(any(Long.class));

    }

    @Test
    @DisplayName("요청받은 id에 해당하는 댓글이 존재하지 않으면 CommentNotFound 예외가 발생한다")
    void removeComment_CommentNotFound(){
        final Long ID = 1L;
        when(commentRepository.selectById(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.removeComment(ID))
                .isInstanceOf(CommentNotFoundException.class)
                .hasMessage(ID + ":해당하는 댓글이 존재하지 않습니다");

        verify(commentRepository, times(1)).selectById(any(Long.class));
        verify(commentRepository, never()).delete(any(Long.class));
    }

    @Test
    @DisplayName("요청 받은 댓글 수정 정보의 postId에 해당하는 포스트가 존재하지 않으면 PostNotFound 예외가 발생한다.")
    void modifyComment_postNotFound(){
        final Long POST_ID = 1L;
        UpdateCommentCommand updateCommentCommand = UpdateCommentCommand.builder()
                .id(1L)
                .postId(POST_ID)
                .userId(1L)
                .content("content").build();
        when(postRepository.selectById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.modifyComment(updateCommentCommand))
                .isInstanceOf(PostNotFoundException.class)
                .hasMessage(POST_ID + ":해당하는 포스트가 존재하지 않습니다");

        verify(postRepository, times(1)).selectById(POST_ID);
        verify(userRepository, never()).selectById(any(Long.class));
        verify(commentRepository, never()).selectById(any(Long.class));
        verify(commentRepository, never()).update(any());
    }

    @Test
    @DisplayName("요청 받은 댓글 수정 정보의 userId에 해당하는 유저가 존재하지 않으면 UserNotFound 예외가 발생한다.")
    void modifyComment_userNotFound(){
        final Long POST_ID = 1L;
        final Long USER_ID = 1L;
        UpdateCommentCommand updateCommentCommand = UpdateCommentCommand.builder()
                .id(1L)
                .postId(POST_ID)
                .userId(USER_ID)
                .content("content").build();

        PostEntity post = PostEntity.builder().id(POST_ID).build();

        when(postRepository.selectById(POST_ID)).thenReturn(Optional.of(post));
        when(userRepository.selectById(USER_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.modifyComment(updateCommentCommand))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(USER_ID + ":해당하는 유저가 존재하지 않습니다");

        verify(postRepository, times(1)).selectById(POST_ID);
        verify(userRepository, times(1)).selectById(USER_ID);
        verify(commentRepository, never()).selectById(any(Long.class));
        verify(commentRepository, never()).update(any());
    }

    @Test
    @DisplayName("요청 받은 댓글 수정 정보의 id에 해당하는 댓글이 존재하지 않으면 CommentNotFound 예외가 발생한다.")
    void modifyComment_commentNotFound(){
        final Long ID = 1L;
        final Long POST_ID = 1L;
        final Long USER_ID = 1L;
        UpdateCommentCommand updateCommentCommand = UpdateCommentCommand.builder()
                .id(ID)
                .postId(POST_ID)
                .userId(USER_ID)
                .content("content").build();

        PostEntity post = PostEntity.builder().id(POST_ID).build();
        UserEntity user = new UserEntity("user1", "1234");

        when(postRepository.selectById(POST_ID)).thenReturn(Optional.of(post));
        when(userRepository.selectById(USER_ID)).thenReturn(Optional.of(user));
        when(commentRepository.selectById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.modifyComment(updateCommentCommand))
                .isInstanceOf(CommentNotFoundException.class)
                .hasMessage(ID + ":해당하는 댓글이 존재하지 않습니다");

        verify(postRepository, times(1)).selectById(POST_ID);
        verify(userRepository, times(1)).selectById(USER_ID);
        verify(commentRepository, times(1)).selectById(ID);
        verify(commentRepository, never()).update(any());
    }

    @Test
    @DisplayName("요청 받은 댓글 수정 정보가 유효하면 해당 정보를 수정하고 수정된 객체를 반환한다.")
    void modifyComment(){
        final Long ID = 1L;
        final Long POST_ID = 1L;
        final Long USER_ID = 1L;
        final String CONTENT = "updated";
        UpdateCommentCommand updateCommentCommand = UpdateCommentCommand.builder()
                .id(ID)
                .postId(POST_ID)
                .userId(USER_ID)
                .content(CONTENT).build();

        PostEntity post = PostEntity.builder().id(POST_ID).build();
        UserEntity user = new UserEntity("user1", "1234");
        CommentEntity existComment = CommentEntity.builder()
                .id(ID)
                .postId(POST_ID)
                .userId(USER_ID)
                .content("before update").build();

        CommentEntity updatedComment = CommentEntity.builder()
                .id(ID)
                .postId(POST_ID)
                .userId(USER_ID)
                .content(CONTENT).build();

        when(postRepository.selectById(POST_ID)).thenReturn(Optional.of(post));
        when(userRepository.selectById(USER_ID)).thenReturn(Optional.of(user));
        when(commentRepository.selectById(ID)).thenReturn(Optional.of(existComment));
        when(commentRepository.update(existComment)).thenReturn(updatedComment);

        Comment comment = commentService.modifyComment(updateCommentCommand);

        assertAll(
                () -> assertThat(comment.getId()).isEqualTo(ID),
                () -> assertThat(comment.getUserId()).isEqualTo(USER_ID),
                () -> assertThat(comment.getPostId()).isEqualTo(POST_ID),
                () -> assertThat(comment.getContent()).isEqualTo(CONTENT)
        );
        verify(postRepository, times(1)).selectById(POST_ID);
        verify(userRepository, times(1)).selectById(USER_ID);
        verify(commentRepository, times(1)).selectById(ID);
        verify(commentRepository, times(1)).update(existComment);
    }
}
