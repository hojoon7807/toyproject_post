package com.flab.posttoy.service;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.Post;
import com.flab.posttoy.dto.PostWithCommentDTO;
import com.flab.posttoy.exception.ResourceNotFoundException;
import com.flab.posttoy.repository.comment.CommentRepository;
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
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
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
    public PostWithCommentDTO getPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("post", "id", id));
        List<Comment> commentList = commentRepository.findByPostId(id);

        // 서로 다른 타입을 담아 return 하는 방법이 있나?? DTO 클래스를 생성??
        //List.of(post, commentList);
        return new PostWithCommentDTO(post, commentList);
    }

    /**
     * 본인 게시글 조회
     */
    public List<Post> getPostListByUserId(Long userId){
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        return postRepository.findByUserId(userId);
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
