package com.flab.posttoy.repository.user;

import com.flab.posttoy.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserMemoryRepository implements UserRepository{
    private final ConcurrentHashMap<Long, User> store = new ConcurrentHashMap();
    private AtomicLong sequence = new AtomicLong();

    @Override
    public User insert(User user) {
        user.setId(sequence.incrementAndGet());
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> selectById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> selectByName(String username) {
        return store.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }

    @Override
    public List<User> selectAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
