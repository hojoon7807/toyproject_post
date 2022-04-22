package com.flab.posttoy.repository.comment;

import com.flab.posttoy.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CommentMemoryRepository implements CommentRepository{

    private final ConcurrentHashMap<Long, Comment> store = new ConcurrentHashMap();
    private AtomicLong sequence = new AtomicLong();

    @Override
    public Comment insert(Comment comment) {
        comment.setId(sequence.incrementAndGet());
        store.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        store.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public Optional<Comment> selectById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Comment> selectByPostId(Long postId) {
        return store.values().stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> selectAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
