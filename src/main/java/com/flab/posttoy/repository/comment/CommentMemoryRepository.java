package com.flab.posttoy.repository.comment;

import com.flab.posttoy.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CommentMemoryRepository implements CommentRepository {

    // HashMap? ConcurrentHashMap??
    private final ConcurrentHashMap<Long, Comment> store = new ConcurrentHashMap<>();
    // 동시성 문제를 고려해 AtomicLong 사용
    private AtomicLong sequence = new AtomicLong();

    @Override
    public Comment save(Comment comment) {
        comment.setId(sequence.incrementAndGet());
        comment.setCreatedAt(createdTime());
        comment.setModifiedAt(comment.getCreatedAt());
        store.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        comment.setModifiedAt(modifiedTime());
        store.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public void remove(Long id) {
        store.remove(id);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<List<Comment>> findByUserame(String username) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        return new ArrayList<>(store.values());
    }
}


