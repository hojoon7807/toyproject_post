package com.flab.posttoy.web.controller;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.service.CreatePostCommand;
import com.flab.posttoy.service.PostService;
import com.flab.posttoy.service.UpdatePostCommand;
import com.flab.posttoy.web.dto.request.CreatePostRequest;
import com.flab.posttoy.web.dto.request.UpdatePostRequest;
import com.flab.posttoy.web.dto.response.PostDetailResponseDTO;
import com.flab.posttoy.web.dto.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDTO> postSave(@RequestBody @Valid CreatePostRequest createPostRequest) {

        Post post = postService.addPost(CreatePostCommand.of(
                createPostRequest.getUserId(),
                createPostRequest.getTitle(),
                createPostRequest.getContent()));

        return new ResponseEntity<>(PostResponseDTO.from(post), HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> postList() {
        List<Post> postList = postService.findAllPosts();
        List<PostResponseDTO> postResponseList = postList.stream().map(PostResponseDTO::from).collect(Collectors.toList());

        return new ResponseEntity(postResponseList, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailResponseDTO> postDetail(@PathVariable @Min(1) Long postId){
        postService.findPost(postId);

        return new ResponseEntity<>(new PostDetailResponseDTO(),HttpStatus.OK);
    }

    @PatchMapping("posts/{postId}")
    public ResponseEntity<PostResponseDTO> postModify(@RequestBody UpdatePostRequest updatePostRequest, @PathVariable @Min(1) Long postId) {
        Post post = postService.modifyPost(UpdatePostCommand.of(
                updatePostRequest.getUserId()
                ,updatePostRequest.getTitle()
                ,updatePostRequest.getContent()), postId);

        return new ResponseEntity<>(PostResponseDTO.from(post), HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<String> postRemove(@PathVariable @Min(1) Long postId) {
        postService.removePost(postId);
        return new ResponseEntity<>("포스트가 정상적으로 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostResponseDTO>> postListByUser(@PathVariable @Min(1) Long userId){
        List<Post> postList = postService.findAllPostsByUserId(userId);
        List<PostResponseDTO> postResponseList = postList.stream().map(PostResponseDTO::from).collect(Collectors.toList());

        return new ResponseEntity(postResponseList, HttpStatus.OK);
    }


}
