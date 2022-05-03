package com.flab.posttoy.repository;

import com.flab.posttoy.domain.port.UserRepository;
import com.flab.posttoy.repository.user.UserEntity;
import com.flab.posttoy.repository.user.UserMemoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserMemoryRepositoryTest {
    private final UserRepository userRepository = new UserMemoryRepository();

    @AfterEach
    void afterEach(){
        userRepository.clearStore();
    }

    @Test
    @DisplayName("회원 추가")
    void insertTest(){
        UserEntity user1 = new UserEntity("member1", "1234");
        UserEntity user2 = new UserEntity("member2", "a1234");

        UserEntity insertedUser1 = userRepository.insert(user1);
        UserEntity insertedUser2 = userRepository.insert(user2);

        assertAll(
                ()-> assertThat(user1.getId()).isEqualTo(insertedUser1.getId()),
                ()-> assertThat(user1.getUsername()).isEqualTo(insertedUser1.getUsername()),
                ()-> assertThat(user1.getPassword()).isEqualTo(insertedUser1.getPassword()),
                ()-> assertThat(user2.getId()).isEqualTo(insertedUser2.getId()),
                ()-> assertThat(user2.getUsername()).isEqualTo(insertedUser2.getUsername()),
                ()-> assertThat(user2.getPassword()).isEqualTo(insertedUser2.getPassword())
                );
    }

    @Test
    @DisplayName("회원 ID로 회원 찾기")
    void selectByIdTest(){
        UserEntity user1 = new UserEntity("member1", "1234");
        UserEntity user2 = new UserEntity("member2", "a1234");

        userRepository.insert(user1);
        userRepository.insert(user2);

        Optional<UserEntity> selectedUser1 = userRepository.selectById(1L);
        Optional<UserEntity> selectedUser2 = userRepository.selectById(2L);
        Optional<UserEntity> selectedUser3 = userRepository.selectById(3L);

        assertAll(
                () -> assertThat(selectedUser1).isNotEmpty().hasValueSatisfying(userEntity ->
                        assertAll(
                                ()-> assertThat(userEntity).isInstanceOf(UserEntity.class),
                                () -> assertThat(userEntity.getId()).isEqualTo(1L)
                                )
                ),
                () -> assertThat(selectedUser2).isNotEmpty().hasValueSatisfying(userEntity ->
                        assertAll(
                                ()-> assertThat(userEntity).isInstanceOf(UserEntity.class),
                                () -> assertThat(userEntity.getId()).isEqualTo(2L)
                        )
                ),
                () -> assertThat(selectedUser3).isEmpty()
        );

    }

    @Test
    @DisplayName("회원 이름으로 회원 찾기")
    void selectByNameTest(){
        UserEntity user1 = new UserEntity("member1", "1234");

        userRepository.insert(user1);

        Optional<UserEntity> selectedUser1 = userRepository.selectByName("member1");
        Optional<UserEntity> selectedUser2 = userRepository.selectByName("member2");

        assertAll(
                () -> assertThat(selectedUser1).isNotEmpty().hasValueSatisfying(userEntity ->
                        assertAll(
                                () -> assertThat(userEntity).isInstanceOf(UserEntity.class),
                                () -> assertThat(userEntity.getUsername()).isEqualTo("member1")
                        )
                ),
                () -> assertThat(selectedUser2).isEmpty()
        );
    }

    @Test
    @DisplayName("전체 회원 조회하기")
    void selectAllTest(){
        UserEntity user1 = new UserEntity("member1", "1234");
        UserEntity user2 = new UserEntity("member2", "a1234");
        UserEntity user3 = new UserEntity("member3", "a1234");

        userRepository.insert(user1);
        userRepository.insert(user2);
        userRepository.insert(user3);

        List<UserEntity> userList = userRepository.selectAll();

        assertThat(userList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("회원 탈퇴하기")
    void deleteTest(){
        UserEntity user1 = new UserEntity("member1", "1234");
        UserEntity insertUser = userRepository.insert(user1);

        userRepository.delete(insertUser.getId());
        Optional<UserEntity> selectedUser = userRepository.selectById(insertUser.getId());

        assertThat(selectedUser).isEmpty();
    }
}
