package com.flab.posttoy.service;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.exception.ResourceNotFoundException;
import com.flab.posttoy.repository.post.PostRepository;
import com.flab.posttoy.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * 포스트 등록
     */
    public Post post(Post post) {
        postRepository.save(post);
        return post;
    }

    /**
     * 포스트 전체 조회
     */
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    /**
     * 포스트 상세 조회
     */
    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("post", "id", id));
    }

    /**
     * 본인 게시글 조회
     */
    public List<Post> getPostListByUserId(Long userId){
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        return postRepository.findAll().stream()
                .filter(post -> post.getUserId() == userId)
                .collect(Collectors.toList());
    }

    /**
     * 포스트 수정
     * 코드 수정 필요~~~~~
     */
    public Post updatePost(Post post, Long id){
        Post existPost = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("post", "id", id));
        existPost.changePost(post.getTitle(), post.getContent());
        return postRepository.save(existPost);
    }

    /**
     * 포스트 삭제
     */
    public void deletePost(Long id) {
        postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("post", "id", id));
        postRepository.remove(id);
    }

}
