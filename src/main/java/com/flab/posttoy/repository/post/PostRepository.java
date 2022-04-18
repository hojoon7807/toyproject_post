package com.flab.posttoy.repository.post;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.repository.MyRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    Optional<Post> findByName(String title);
    List<Post> findByUserId(Long id);
    List<Post> findAll();
    void remove(Long id);

    default LocalDateTime createdTime(){
        return LocalDateTime.now();
    }

    default LocalDateTime modifiedTime(){
        return LocalDateTime.now();
    }
}
