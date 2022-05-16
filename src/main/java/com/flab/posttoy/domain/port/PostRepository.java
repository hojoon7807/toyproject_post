package com.flab.posttoy.domain.port;

import com.flab.posttoy.repository.post.PostEntity;
import com.flab.posttoy.repository.post.PostMemoryRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    PostEntity insert(PostEntity post);
    PostEntity update(PostEntity post);
    void delete(Long id);
    Optional<PostEntity> selectById(Long id);
    List<PostEntity> selectByUserId(Long userId);
    List<PostEntity> selectAll();
}
