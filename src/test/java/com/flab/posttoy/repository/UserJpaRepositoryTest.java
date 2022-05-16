package com.flab.posttoy.repository;

import com.flab.posttoy.repository.user.UserDataJpaRepository;
import com.flab.posttoy.repository.user.UserJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
@Slf4j
@ExtendWith(SpringExtension.class)
//@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@DataJpaTest
public class UserJpaRepositoryTest {

    @Autowired
    private UserDataJpaRepository userRepository;

    @Test
    @DisplayName("유저 정보가 유효하면 해당 정보를 성공적으로 저장한다")
    void save(){
        final String USERNAME = "hojoon";
        final String PASSWORD = "1234";
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setUsername(USERNAME);
        userJpaEntity.setPassword(PASSWORD);

        UserJpaEntity user = userRepository.insert(userJpaEntity);
        userRepository.selectById(user.getId()).ifPresent(findUser ->
                assertAll(
                        () -> assertThat(findUser.getId()).isEqualTo(1L),
                        () -> assertThat(findUser.getUsername()).isEqualTo(USERNAME),
                        () -> assertThat(findUser.getPassword()).isEqualTo(PASSWORD)
                )
        );
    }

    @Test
    @DisplayName("ID가 유효하면 ID에 해당하는 유저를 삭제한다")
    void delete(){
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setUsername("hojoon");
        userJpaEntity.setPassword("1234");

        UserJpaEntity user = userRepository.insert(userJpaEntity);
        userRepository.selectById(user.getId()).ifPresent(user1->
                log.info("username : {}", user1.getUsername())
        );

        userRepository.delete(user.getId());
        userRepository.selectById(user.getId()).ifPresentOrElse(user2 ->
                log.info("username : {}", user2.getUsername()),
                () -> log.info("empty"));

        assertThat(userRepository.selectById(user.getId())).isEmpty();
    }

    @Test
    @DisplayName("유저이름이 유효하면 이름에 해당하는 유저를 반환한다")
    void selectByUsername(){
        final String USERNAME = "hojoon";
        final String PASSWORD = "1234";
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setUsername(USERNAME);
        userJpaEntity.setPassword(PASSWORD);

        userRepository.insert(userJpaEntity);
        Optional<UserJpaEntity> findUser = userRepository.selectByUsername(USERNAME);

        assertAll(
                () -> assertThat(findUser).isPresent(),
                () -> assertThat(findUser).hasValueSatisfying(user ->
                        assertThat(user.getUsername()).isEqualTo(USERNAME)
                )
        );
    }

    @Test
    @DisplayName("ID가 유효하면 ID에 해당하는 유저를 반환한다")
    void selectById(){
        final String USERNAME = "hojoon";
        final String PASSWORD = "1234";
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setUsername(USERNAME);
        userJpaEntity.setPassword(PASSWORD);
        userRepository.insert(userJpaEntity);

        Optional<UserJpaEntity> findUser = userRepository.selectById(userJpaEntity.getId());

        assertAll(
                () -> assertThat(findUser).isPresent(),
                () -> assertThat(findUser).hasValueSatisfying(user ->
                        assertAll(
                                () -> assertThat(user).isEqualTo(userJpaEntity),
                                () -> assertThat(user.getUsername()).isEqualTo(USERNAME)
                        )
                )
        );
    }
}
