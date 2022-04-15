package com.flab.posttoy.repository.comment;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.repository.MyRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    Optional<List<Comment>> findByUserame(String username);

    // comment에서의 findByName의 필요성 필요없다면 어떻게?
    Optional<Comment> findByName(String content);

    List<Comment> findAll();
}
