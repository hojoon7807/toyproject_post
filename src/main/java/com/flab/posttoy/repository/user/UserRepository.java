package com.flab.posttoy.repository.user;

import com.flab.posttoy.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User insert(User user);
    void delete(Long id);
    Optional<User> selectById(Long id);
    List<User> selectAll();

    void clearStore();

}
