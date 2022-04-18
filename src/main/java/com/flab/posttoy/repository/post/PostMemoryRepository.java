package com.flab.posttoy.repository.post;

import com.flab.posttoy.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostMemoryRepository implements PostRepository {

    // HashMap? ConcurrentHashMap??
    private final ConcurrentHashMap<Long, Post> store = new ConcurrentHashMap<>();

    // 동시성 문제를 고려해 AtomicLong 사용
    private AtomicLong sequence = new AtomicLong();

    @Override
    public Post save(Post post) {
        // findById를 써야되나??
        // store에 postid의 키가 존재하면 values 교체->update, 아니면 save,  save에 2개의 책임을 주는 것인가??
        if(store.containsKey(post.getId())){
            post.setModifiedAt(modifiedTime());
            store.put(post.getId(), post);
        } else {
            post.setId(sequence.incrementAndGet());
            post.setCreatedAt(createdTime());
            post.setModifiedAt(post.getCreatedAt());
            store.put(post.getId(), post);
        }

        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Post> findByName(String title) {
        return store.values().stream()
                .filter(post -> post.getTitle().equals(title))
                .findAny();
    }

    @Override
    public List<Post> findByUserId(Long id) {
        return findAll().stream()
                .filter(post -> post.getUserId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void remove(Long id) {
        store.remove(id);
    }
}