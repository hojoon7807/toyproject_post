package com.flab.posttoy.repository.user;

import com.flab.posttoy.entity.UserEntity;

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
