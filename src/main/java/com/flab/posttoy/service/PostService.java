package com.flab.posttoy.service;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.Post;
import com.flab.posttoy.domain.PostDetail;
import com.flab.posttoy.exception.post.PostNotFoundException;
import com.flab.posttoy.exception.user.UserNotFoundException;
import com.flab.posttoy.domain.port.CommentRepository;
import com.flab.posttoy.domain.port.PostRepository;
import com.flab.posttoy.domain.port.UserRepository;
import com.flab.posttoy.repository.post.PostEntity;
import com.flab.posttoy.repository.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository<UserEntity, Long> userRepository;
    private final CommentRepository commentRepository;

    public Post addPost(CreatePostCommand createPostCommand) {
        userRepository.selectById(createPostCommand.getUserId()).orElseThrow(()->
                new UserNotFoundException("해당 유저가 존재하지 않습니다")
        );

        PostEntity postEntity = postRepository.insert(createPostCommand.toEntity());
        return postEntity.toDomain();
    }

    public List<Post> findAllPosts() {
        List<Post> postList = postRepository.selectAll().stream()
                .map(PostEntity::toDomain)
                .collect(Collectors.toList());
        return postList;
    }

    public PostDetail findPost(Long id){
        PostEntity existPost = postRepository.selectById(id).orElseThrow(() ->
                new PostNotFoundException("해당 포스트가 존재하지 않습니다")
        );

        // mapper를 사용하지 않을 경우 Entity에서 toDomain?? Comment에서 from(CommentEntity)??
        List<Comment> commentList = commentRepository.selectByPostId(existPost.getId()).stream()
                .map(commentEntity -> Comment.builder()
                        .id(commentEntity.getId())
                        .userId(commentEntity.getUserId())
                        .postId(commentEntity.getPostId())
                        .content(commentEntity.getContent())
                        .build())
                .collect(Collectors.toList());

        // comment refectoring 후 작성
        return PostDetail.of(existPost.toDomain(),commentList);
    }

    public List<Post> findAllPostsByUserId(Long userId){
            userRepository.selectById(userId).orElseThrow(()->
                    new UserNotFoundException("해당 유저가 존재하지 않습니다")
            );
        List<Post> postList = postRepository.selectByUserId(userId).stream()
                .map(PostEntity::toDomain)
                .collect(Collectors.toList());

        return postList;
    };

    public Post modifyPost(UpdatePostCommand updatePostCommand, Long id) {
        PostEntity existPost = postRepository.selectById(id).orElseThrow(() ->
                new PostNotFoundException("해당 포스트가 존재하지 않습니다")
        );

        existPost.changePost(updatePostCommand.getTitle(), updatePostCommand.getContent());

        return existPost.toDomain();
    }

    public void removePost(Long id){
        postRepository.selectById(id).orElseThrow(() ->
                new PostNotFoundException("해당 포스트가 존재하지 않습니다")
        );
        postRepository.delete(id);
    }
}
