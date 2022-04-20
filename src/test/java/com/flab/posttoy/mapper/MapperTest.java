package com.flab.posttoy.mapper;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.dto.UserDTO;
import com.flab.posttoy.dto.mapper.UserMapper;
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

        UserDTO userDTO = mapper.toDto(user);
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

        User user = mapper.toEntity(userDTO);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("hojoon");
        assertThat(user.getPassword()).isEqualTo("1234");
    }
}
