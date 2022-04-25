package com.flab.posttoy.service;

import com.flab.posttoy.domain.Comment;
import com.flab.posttoy.dto.CommentDTO;
import com.flab.posttoy.dto.UpdateCommentDTO;

public interface ICommentService {
    CommentDTO addComment(CommentDTO commentDTO);

    CommentDTO modifyComment(UpdateCommentDTO updateCommentDTO, Long id);
    CommentDTO modifyComment(Long commentId, String contents);

    void removeComment(Long id);
}
