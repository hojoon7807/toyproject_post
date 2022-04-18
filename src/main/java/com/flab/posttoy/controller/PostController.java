package com.flab.posttoy.controller;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.domain.User;
import com.flab.posttoy.dto.PostDTO;
import com.flab.posttoy.dto.PostWithCommentDTO;
import com.flab.posttoy.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostDTO.ResponsePost> createPost(@RequestBody PostDTO.RequestPost requestPostDTO){
        Post post = postService.post(requestPostDTO.toEntity());
        return new ResponseEntity<>(PostDTO.ResponsePost.from(post), HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO.ResponsePost>> postList(){
        List<Post> postList = postService.getPostList();
        List<PostDTO.ResponsePost> responseListDTO = postList.stream().map(PostDTO.ResponsePost::from).collect(Collectors.toList());
        return new ResponseEntity<>(responseListDTO, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostWithCommentDTO> postDetail (@PathVariable Long postId){
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("포스트가 삭제되었습니다.", HttpStatus.OK);
    }

    @PatchMapping("/posts/{postId}")
    public ResponseEntity<PostDTO.ResponsePost> updatePost(@PathVariable Long postId, @RequestBody PostDTO.RequestPost requestPost) {
        Post post = postService.updatePost(requestPost.toEntity(), postId);
        return new ResponseEntity<>(PostDTO.ResponsePost.from(post), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostDTO.ResponsePost>> postListByUser(@PathVariable Long userId){
        List<Post> postList = postService.getPostListByUserId(userId);
        List<PostDTO.ResponsePost> responsePostList = postList.stream().map(PostDTO.ResponsePost::from).collect(Collectors.toList());
        return new ResponseEntity<>(responsePostList, HttpStatus.OK);
    }
}
