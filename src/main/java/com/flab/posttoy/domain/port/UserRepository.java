package com.flab.posttoy.domain.port;

import com.flab.posttoy.repository.user.UserEntity;
import com.flab.posttoy.repository.user.UserMemoryRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity insert(UserEntity user);
    void delete(Long id);
    Optional<UserEntity> selectById(Long id);
    Optional<UserEntity> selectByName(String username);
    List<UserEntity> selectAll();

    void clearStore();

}
