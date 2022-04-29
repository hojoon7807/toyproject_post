package com.flab.posttoy.service;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.exception.post.PostNotFoundException;
import com.flab.posttoy.exception.user.UserNotFoundException;
import com.flab.posttoy.mapper.PostMapper;
import com.flab.posttoy.domain.port.CommentRepository;
import com.flab.posttoy.domain.port.PostRepository;
import com.flab.posttoy.domain.port.UserRepository;
import com.flab.posttoy.repository.post.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostMapper postMapper;

    public Post addPost(CreatePostCommand createPostCommand) {
        userRepository.selectById(createPostCommand.getUserId()).orElseThrow(()->
                new UserNotFoundException("해당 유저가 존재하지 않습니다")
        );

        PostEntity postEntity = postRepository.insert(createPostCommand.toEntity());
        return Post.from(postEntity);
    }

    public List<Post> findAllPosts() {
        List<Post> postList = postRepository.selectAll().stream()
                .map(postEntity -> postMapper.toPost(postEntity))
                .collect(Collectors.toList());
        return postList;
    }

    public Post findPost(Long id){
        Post post = postMapper.toPost(postRepository.selectById(id).orElseThrow(() ->
                new PostNotFoundException("해당 포스트가 존재하지 않습니다")
        ));
        // comment refectoring 후 작성
        return post;
    }

    public List<Post> findAllPostsByUserId(Long userId){
            userRepository.selectById(userId).orElseThrow(()->
                    new UserNotFoundException("해당 유저가 존재하지 않습니다")
            );
        List<Post> postList = postRepository.selectByUserId(userId).stream()
                .map(postEntity -> postMapper.toPost(postEntity))
                .collect(Collectors.toList());

        return postList;
    };

    public Post modifyPost(UpdatePostCommand updatePostCommand, Long id) {
        PostEntity existPost = postRepository.selectById(id).orElseThrow(() ->
                new PostNotFoundException("해당 포스트가 존재하지 않습니다")
        );

        existPost.changePost(updatePostCommand.getTitle(), updatePostCommand.getContent());

        return Post.from(postRepository.update(existPost));
    }

    public void removePost(Long id){
        postRepository.selectById(id).orElseThrow(() ->
                new PostNotFoundException("해당 포스트가 존재하지 않습니다")
        );
        postRepository.delete(id);
    }
}
