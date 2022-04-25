package com.flab.posttoy.web.controller;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.dto.CommentDTO;
import com.flab.posttoy.dto.mapper.CommentMapper;
import com.flab.posttoy.service.ICommentService;
import com.flab.posttoy.web.dto.mapper.WebCommentMapper;
import com.flab.posttoy.web.dto.request.ModifyCommentReqeust;
import com.flab.posttoy.web.dto.request.RequestCommentDTO;
import com.flab.posttoy.web.dto.response.ResponseCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final ICommentService commentService;
    private final WebCommentMapper commentMapper;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ResponseCommentDTO> commentSave(@RequestBody @Valid RequestCommentDTO requestCommentDTO){
        CommentDTO commentDTO = commentService.addComment(commentMapper.toCommentDto(requestCommentDTO));
        ResponseCommentDTO responseCommentDTO = commentMapper.toResponseCommentDto(commentDTO);
        return ResponseEntity.created(URI.create("/posts/"+responseCommentDTO.getPostId())).body(responseCommentDTO);
    }

    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ResponseCommentDTO> commentModify(@RequestBody @Valid ModifyCommentReqeust modifyRequest, @PathVariable Long commentId) {
//        CommentDTO commentDTO = commentService.modifyComment(commentMapper.toUpdateCommentDto(modifyRequest), commentId);

        //원래 코드 DTO 를 DTO 로 transform ?
        //CommentDTO commentDTO = commentService.modifyComment(commentId, modifyRequest.getContent());
        //ResponseCommentDTO responseCommentDTO = commentMapper.toResponseCommentDto(commentDTO);

        Comment comment = commentService.modifyComment(commentId, modifyRequest.getContent());
        ResponseCommentDTO responseCommentDTO = commentMapper.toResponseCommentDto(comment);

        return new ResponseEntity<>(responseCommentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> commentRemove(@PathVariable Long commentId) {
        commentService.removeComment(commentId);
        return new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.OK);
    }
}
