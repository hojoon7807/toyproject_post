package com.flab.posttoy.web.controller;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.service.CommentService;
import com.flab.posttoy.web.dto.request.CreateCommentRequest;
import com.flab.posttoy.web.dto.request.SimpleMessageResponse;
import com.flab.posttoy.web.dto.request.SimpleMessageResponse;
import com.flab.posttoy.web.dto.request.UpdateCommentRequest;
import com.flab.posttoy.web.dto.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> commentSave(@RequestBody @Valid CreateCommentRequest createCommentRequest){
        Comment comment = commentService.addComment(createCommentRequest.toCommand());
        return new ResponseEntity<>(CommentResponseDTO.from(comment),HttpStatus.CREATED);
    }

    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> commentModify(@RequestBody @Valid UpdateCommentRequest updateCommentRequest,
                                                            @PathVariable Long commentId,
                                                            @PathVariable Long postId) {
        Comment comment = commentService.modifyComment(updateCommentRequest.toCommand(commentId,postId));
        return new ResponseEntity<>(CommentResponseDTO.from(comment), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<SimpleMessageResponse> commentRemove(@PathVariable Long commentId) {
        commentService.removeComment(commentId);
        return new ResponseEntity<>(new SimpleMessageResponse(HttpStatus.OK.value(), commentId+": 댓글이 삭제되었습니다."),HttpStatus.OK);
    }
}
