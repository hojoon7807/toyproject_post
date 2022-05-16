package com.flab.posttoy.repository.user;

import com.flab.posttoy.domain.port.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDataJpaRepository extends JpaRepository<UserJpaEntity, Long>, UserRepository<UserJpaEntity,Long> {
    @Override
    default UserJpaEntity insert(UserJpaEntity o) {
        return save(o);
    }

    @Override
    default void delete(Long aLong) {
        findById(aLong).ifPresent(userJpaEntity -> delete(userJpaEntity));
    }

    @Override
    default Optional<UserJpaEntity> selectById(Long aLong) {
        return findById(aLong);
    }

    @Override
    default Optional<UserJpaEntity> selectByUsername(String username) {
        return findByUsername(username);
    }

    @Override
    default List<UserJpaEntity> selectAll() {
        return findAll();
    }

    Optional<UserJpaEntity> findByUsername(String username);
}
