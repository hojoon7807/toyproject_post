package com.flab.posttoy.mapper;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.repository.user.UserEntity;
import com.flab.posttoy.repository.user.UserMemoryRepository;
import com.flab.posttoy.web.dto.request.RequestUserDTO;
import com.flab.posttoy.web.dto.response.ResponseUserDTO;
import com.flab.posttoy.web.mapper.WebUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Slf4j
public class MapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final WebUserMapper webUserMapper = Mappers.getMapper(WebUserMapper.class);


    @Test
    @DisplayName("requestUser를 User로 변환 테스트")
    void requestUserToUserMapperTest(){
        RequestUserDTO requestUserDTO = new RequestUserDTO();
        requestUserDTO.setUsername("hojoon");
        requestUserDTO.setPassword("1234");

        User user = webUserMapper.toUser(requestUserDTO);
        log.info("user = {}", user);

        assertAll(
                () -> assertThat(user.getId()).isNull(),
                () -> assertThat(user.getUsername()).isEqualTo("hojoon"),
                () -> assertThat(user.getPassword()).isEqualTo("1234")
                );

    }

    @Test
    @DisplayName("User를 entity로 변환 테스트")
    void userToEntityMapperTest(){
        User user = new User( 0L,"hojoon", "1234");

        UserEntity userEntity = userMapper.toUserEntity(user);
        log.info("user = {}", userEntity.getUsername());

        assertAll(
                () -> assertThat(userEntity.getId()).isEqualTo(0L),
                () -> assertThat(userEntity.getUsername()).isEqualTo("hojoon"),
                () -> assertThat(userEntity.getPassword()).isEqualTo("1234")
        );
    }

    @Test
    @DisplayName("UserEntity를 User로 변환 테스트")
    void entityToUserMapperTest(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("hojoon");
        userEntity.setPassword("1234");

        User user = userMapper.toUser(userEntity);
        log.info("user = {}", user.getId());

        assertAll(
                () -> assertThat(user.getId()).isEqualTo(1L),
                () -> assertThat(user.getUsername()).isEqualTo("hojoon"),
                () -> assertThat(user.getPassword()).isEqualTo("1234")
        );
    }

    @Test
    @DisplayName("user를 responseUser로 변환 테스트")
    void userToResponseUserMapperTest(){
        User user = new User( 1L,null, "1234");

        ResponseUserDTO responseUserDTO = webUserMapper.toResponseUser(user);
        log.info("user = {}", responseUserDTO.getUsername());

        assertAll(
                () -> assertThat(responseUserDTO.getId()).isEqualTo(1L),
                () -> assertThat(responseUserDTO.getUsername()).isEqualTo("hojoon")
        );
    }

}
