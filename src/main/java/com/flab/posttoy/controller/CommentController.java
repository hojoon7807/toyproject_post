package com.flab.posttoy.controller;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.dto.CommentDTO;
import com.flab.posttoy.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments}")
    public ResponseEntity<CommentDTO.ResponseComment> createComment(@RequestBody CommentDTO.RequestComment requestComment, @PathVariable Long postId) {
        Comment comment = commentService.addComment(requestComment.toEntity(postId));
        return new ResponseEntity<>(CommentDTO.ResponseComment.from(comment), HttpStatus.CREATED);
    }

    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO.ResponseComment> updateComment(@RequestBody CommentDTO.RequestComment requestComment, @PathVariable Long commentId) {
        Comment comment = commentService.updateComment(requestComment.toEntity(), commentId);
        return new ResponseEntity<>(CommentDTO.ResponseComment.from(comment), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.OK);
    }

}
