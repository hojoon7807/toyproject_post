package com.flab.posttoy.mapper;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.repository.post.PostEntity;
import com.flab.posttoy.repository.post.PostMemoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Slf4j
public class PostMapperTest {
    private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Test
    @DisplayName("id가 없는 post를 postEntity로 변환 테스트")
    void nullIdPostToPostEntity(){
        Post post = Post.builder()
                .userId(1L)
                .title("hi")
                .content("hi")
                .build();

        PostEntity postEntity = postMapper.toPostEntity(post);
        log.info("postEntity id: {}",postEntity.getId());

        assertAll(
                ()-> assertThat(postEntity.getId()).isEqualTo(0L),
                ()-> assertThat(postEntity.getUserId()).isEqualTo(1L),
                ()-> assertThat(postEntity.getTitle()).isEqualTo("hi"),
                ()-> assertThat(postEntity.getContent()).isEqualTo("hi")
        );
    }

    @Test
    @DisplayName("id가 있는 post를 postEntity로 변환 테스트")
    void hasIdPostToPostEntity(){
        Post post = Post.builder()
                .id(1L)
                .userId(1L)
                .title("hi")
                .content("hi")
                .build();

        PostEntity postEntity = postMapper.toPostEntity(post);
        log.info("post id: {}",post.getId());
        log.info("postEntity id: {}",postEntity.getId());

        assertAll(
                ()-> assertThat(postEntity.getId()).isEqualTo(1L),
                ()-> assertThat(postEntity.getUserId()).isEqualTo(1L),
                ()-> assertThat(postEntity.getTitle()).isEqualTo("hi"),
                ()-> assertThat(postEntity.getContent()).isEqualTo("hi")
        );
    }

    @Test
    @DisplayName("postEntity를 post로 변환 테스트")
    void PostEntityToPost(){
        PostEntity postEntity = PostEntity.builder()
                .id(1L)
                .userId(1L)
                .title("hi")
                .content("hi")
                .build();

        Post post = postMapper.toPost(postEntity);
        log.info("post id: {}",post.getId());
        log.info("postEntity id: {}",postEntity.getId());

        assertAll(
                ()-> assertThat(postEntity.getId()).isEqualTo(1L),
                ()-> assertThat(postEntity.getUserId()).isEqualTo(1L),
                ()-> assertThat(postEntity.getTitle()).isEqualTo("hi"),
                ()-> assertThat(postEntity.getContent()).isEqualTo("hi")
        );
    }


}
