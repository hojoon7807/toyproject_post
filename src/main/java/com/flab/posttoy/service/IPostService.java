package com.flab.posttoy.service;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.web.dto.PostDetailDTO;

import java.util.List;

public interface IPostService {
    Post addPost(Post post);
    Post modifyPost(Post post,Long id);
    void removePost(Long id);

    PostDetailDTO findPost(Long id);
    List<Post> findAllPostsByUserId(Long userId);
    List<Post> findAllPosts();

}
