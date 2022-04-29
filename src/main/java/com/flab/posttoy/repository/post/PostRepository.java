package com.flab.posttoy.repository.post;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.domain.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post insert(Post post);
    Post update(Post post);
    void delete(Long id);
    Optional<Post> selectById(Long id);
    List<Post> selectByUserId(Long userId);
    List<User> selectAll();

    void clearStore();

}
