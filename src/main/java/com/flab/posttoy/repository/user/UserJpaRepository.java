package com.flab.posttoy.repository.user;

import com.flab.posttoy.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("userJpaRepository")
@RequiredArgsConstructor
public class UserJpaRepository implements UserRepository<UserJpaEntity,Long> {

    private final EntityManager em;

    @Override
    public UserJpaEntity insert(UserJpaEntity userJpaEntity) {
        em.persist(userJpaEntity);
        return userJpaEntity;
    }

    @Override
    public void delete(Long id) {
        UserJpaEntity findUser = em.find(UserJpaEntity.class, id);
        if (findUser != null) {
            em.remove(findUser);
        }
    }

    @Override
    public Optional<UserJpaEntity> selectById(Long id) {
        return Optional.ofNullable(em.find(UserJpaEntity.class, id));
    }

    @Override
    public Optional<UserJpaEntity> selectByUsername(String username) {
        UserJpaEntity user = em.createQuery("select u from UserJpaEntity u where u.username = :username", UserJpaEntity.class)
                .setParameter("username", username)
                .getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    public List<UserJpaEntity> selectAll() {
        return em.createQuery("select u from UserJpaEntity u", UserJpaEntity.class)
                .getResultList();
    }
}
