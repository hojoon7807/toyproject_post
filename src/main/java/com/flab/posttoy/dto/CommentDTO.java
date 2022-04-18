package com.flab.posttoy.dto;

import com.flab.posttoy.domain.Comment;
import lombok.Getter;

public class CommentDTO {

    @Getter
    public static class RequestComment{
        private Long userId;
        private Long postId;
        private String content;

        // patch 요청시 사용
        public Comment toEntity(){
            return Comment.builder()
                    .userId(userId)
                    .postId(postId)
                    .content(content)
                    .build();
        }

        public Comment toEntity(Long postId){
            return Comment.builder()
                    .userId(userId)
                    .postId(postId)
                    .content(content)
                    .build();
        }
    }

    public static class ResponseComment{
        private Long userId;
        private Long postId;
        private String content;


        public ResponseComment(Long userId, Long postId, String content) {
            this.userId = userId;
            this.postId = postId;
            this.content = content;
        }

        public static ResponseComment from(Comment comment) {
            return new ResponseComment(comment.getUserId(), comment.getPostId(), comment.getContent());
        }
    }
}
