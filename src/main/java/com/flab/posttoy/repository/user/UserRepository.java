package com.flab.posttoy.repository.user;

import com.flab.posttoy.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findAll();

    default LocalDateTime createdTime(){
        return LocalDateTime.now();
    }

    default LocalDateTime modifiedTime(){
        return LocalDateTime.now();
    }
}
