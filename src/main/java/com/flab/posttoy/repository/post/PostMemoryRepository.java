package com.flab.posttoy.repository.post;

import com.flab.posttoy.domain.port.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostMemoryRepository implements PostRepository {

    private final ConcurrentHashMap<Long, PostEntity> store = new ConcurrentHashMap<>();
    private AtomicLong sequence = new AtomicLong();

    @Override
    public PostEntity insert(PostEntity post) {
        post.setId(sequence.incrementAndGet());
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public PostEntity update(PostEntity post) {
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public Optional<PostEntity> selectById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<PostEntity> selectByUserId(Long userId) {
        return store.values().stream()
                .filter(post -> post.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostEntity> selectAll() {
        return new ArrayList<>(store.values());
    }

}
