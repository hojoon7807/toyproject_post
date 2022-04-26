package com.flab.posttoy.repository.post;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.domain.User;
import com.flab.posttoy.entity.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    PostEntity insert(PostEntity post);
    PostEntity update(PostEntity post);
    void delete(Long id);
    Optional<PostEntity> selectById(Long id);
    List<PostEntity> selectByUserId(Long userId);
    List<PostEntity> selectAll();

    void clearStore();

}
