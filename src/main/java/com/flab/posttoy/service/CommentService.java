package com.flab.posttoy.service;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.domain.port.UserRepository;
import com.flab.posttoy.exception.comment.CommentNotFoundException;
import com.flab.posttoy.exception.post.PostNotFoundException;
import com.flab.posttoy.domain.port.CommentRepository;
import com.flab.posttoy.domain.port.PostRepository;
import com.flab.posttoy.repository.comment.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment addComment(CreateCommentCommand createCommentCommand) {
        validateExistPost(createCommentCommand.getPostId());
        CommentEntity commentEntity = commentRepository.insert(createCommentCommand.toEntity());

        return Comment.builder()
                .id(commentEntity.getId())
                .postId(commentEntity.getPostId())
                .userId(commentEntity.getUserId())
                .content(commentEntity.getContent())
                .build();
    }

    public Comment modifyComment(UpdateCommentCommand updateCommentCommand) {
        validateExistPost(updateCommentCommand.getPostId());
        validateExistUser(updateCommentCommand.getUserId());
        CommentEntity existComment = validateExistComment(updateCommentCommand.getId());
        existComment.changeComment(updateCommentCommand.getContent());
        CommentEntity updatedComment = commentRepository.update(existComment);
        return Comment.builder()
                .id(updatedComment.getId())
                .postId(updatedComment.getPostId())
                .userId(updatedComment.getUserId())
                .content(updatedComment.getContent())
                .build();
    }

    public void removeComment(Long id) {
        CommentEntity commentEntity = validateExistComment(id);
        commentRepository.delete(commentEntity.getId());
    }

    private CommentEntity validateExistComment(Long id) {
        return commentRepository.selectById(id).orElseThrow(() ->
                new CommentNotFoundException(id + ": 해당하는 댓글이 존재하지 않습니다."));
    }

    private void validateExistPost(Long postId) {
        postRepository.selectById(postId).orElseThrow(()->
                new PostNotFoundException(postId + ":해당하는 포스트가 존재하지 않습니다"));
    }

    private void validateExistUser(Long userId) {
        userRepository.selectById(userId).orElseThrow(()->
                new PostNotFoundException(userId + ":해당하는 유저가 존재하지 않습니다"));
    }
}
