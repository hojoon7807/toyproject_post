package com.flab.posttoy.service;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.exception.ResourceNotFoundException;
import com.flab.posttoy.repository.comment.CommentRepository;
import com.flab.posttoy.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * 댓글 추가
     */
    public Comment addComment(Comment comment) {
        // post 존재 여부 검증이 필요한가??
        postRepository.findById(comment.getPostId()).orElseThrow(() -> new ResourceNotFoundException("post", "id", comment.getPostId()));
        return commentRepository.save(comment);
    }

    /**
     * 댓글 수정
     */
    public Comment updateComment(Comment comment, Long commentId) {
        // post 존재 여부 검증이 필요한가??
        postRepository.findById(comment.getPostId()).orElseThrow(() -> new ResourceNotFoundException("post", "id", comment.getPostId()));
        Comment existComment = commentRepository.findById(commentId).orElseThrow(() ->
            new ResourceNotFoundException("comment", "id", commentId)
        );
        existComment.changeComment(comment.getContent());
        return commentRepository.update(existComment);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Long id) {
        commentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("comment", "id", id)
        );
        commentRepository.remove(id);
    }

}
