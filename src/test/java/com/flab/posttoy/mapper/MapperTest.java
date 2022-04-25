package com.flab.posttoy.mapper;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.User;
import com.flab.posttoy.dto.CommentDTO;
import com.flab.posttoy.dto.UserDTO;
import com.flab.posttoy.domain.mapper.CommentMapper;
import com.flab.posttoy.dto.mapper.CommentMapperImpl;
import com.flab.posttoy.domain.mapper.UserMapper;
import com.flab.posttoy.dto.mapper.UserMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


public class MapperTest {

    @Test
    @DisplayName("User 엔티티를 DTO로 변환 테스트")
    void userToDtoMapperTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(UserMapperImpl.class);
        UserMapper mapper = ac.getBean(UserMapper.class);

        User user = User.builder()
                .id(1L)
                .username("hojoon")
                .password("1234")
                .build();

        UserDTO userDTO = mapper.toUserDto(user);
        assertThat(userDTO.getId()).isEqualTo(1L);
        assertThat(userDTO.getUsername()).isEqualTo("hojoon");
        assertThat(userDTO.getPassword()).isEqualTo("1234");
    }

    @Test
    @DisplayName("UserDTO를 User로 변환 테스트")
    void DtoToUserMapperTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(UserMapperImpl.class);
        UserMapper mapper = ac.getBean(UserMapper.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("hojoon");
        userDTO.setPassword("1234");

        User user = mapper.toUser(userDTO);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("hojoon");
        assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    @DisplayName("Comment 엔티티를 DTO로 변환 테스트")
    void commentToDtoMapperTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(CommentMapperImpl.class);
        CommentMapper mapper = ac.getBean(CommentMapperImpl.class);

        Comment comment = Comment.builder()
                .id(1L)
                .content("comment")
                .userId(1L)
                .postId(1L)
                .build();

        CommentDTO commentDto = mapper.toCommentDto(comment);
        assertThat(commentDto.getId()).isEqualTo(1L);
        assertThat(commentDto.getPostId()).isEqualTo(1L);
        assertThat(commentDto.getUserId()).isEqualTo(1L);
        assertThat(commentDto.getContent()).isEqualTo("comment");
    }
}
