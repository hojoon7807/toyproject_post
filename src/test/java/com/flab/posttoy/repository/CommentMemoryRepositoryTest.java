package com.flab.posttoy.repository;

import com.flab.posttoy.domain.port.CommentRepository;
import com.flab.posttoy.repository.comment.CommentEntity;
import com.flab.posttoy.repository.comment.CommentMemoryRepository;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("CommentMemoryRepository 클래스")
public class CommentMemoryRepositoryTest {
    private CommentRepository commentRepository;
    final String TEST_COMMENT_CONTENT = "content1";


    @BeforeEach
    void beforeEach(){
        commentRepository = new CommentMemoryRepository();
    }

    @Nested
    @DisplayName("insert 메소드는")
    class Describe_insert{

        @Nested
        @DisplayName("CommentEntity 객체가 주어지면")
        class Context_with_a_comment{
            private CommentEntity comment = CommentEntity.builder()
                    .userId(1L)
                    .postId(1L)
                    .content(TEST_COMMENT_CONTENT)
                    .build();

            @Test
            @DisplayName("주어진 객체를 저장하고, 저장된 객체를 반환한다")
            void it_inserts_obj_and_returns_a_inserted_obj(){

                CommentEntity insertedComment = commentRepository.insert(comment);

                assertAll(
                        () -> assertThat(insertedComment.getId()).isEqualTo(1L),
                        () -> assertThat(insertedComment.getUserId()).isEqualTo(1L),
                        () -> assertThat(insertedComment.getPostId()).isEqualTo(1L),
                        () -> assertThat(insertedComment.getContent()).isEqualTo(TEST_COMMENT_CONTENT)
                );
            }
        }
    }

    @Nested
    @DisplayName("selectById 메소드는")
    class Describe_selectById{

        @BeforeEach
        void preprare(){
            insertComment();
        }

        @Nested
        @DisplayName("저장되어 있지 않은 id가 주어진다면")
        class Context_with_a_not_saved_id{

            final Long NOT_SAVED_ID = 2L;

            @Test
            @DisplayName("주어진 id로 조회하고, null 객체를 optional로 리턴한다")
            void it_returns_optional_null_obj() {
                Optional<CommentEntity> comment = commentRepository.selectById(NOT_SAVED_ID);

                assertThat(comment).isEmpty();
            }
        }

        @Nested
        @DisplayName("저장되어 있는 id가 주어진다면")
        class Context_with_a_saved_id{

            final Long SAVED_ID = 1L;

            @Test
            @DisplayName("주어진 id로 조회하고, 조회한 객체를 optional로 리턴한다")
            void it_returns_optional_find_obj(){

                Optional<CommentEntity> comment = commentRepository.selectById(SAVED_ID);

                assertThat(comment).isPresent().hasValueSatisfying(
                                c -> assertThat(c.getId()).isEqualTo(SAVED_ID)
                );
            }
        }
    }

    @Nested
    @DisplayName("update 메소드는")
    class Describe_update{

        @Nested
        @DisplayName("저장되어 있는 객체와 변경할 content가 주어지면")
        class Context_with_a_update_comment{
            final String UPDATE_CONTENT = "update content";
            CommentEntity givenComment;

            @BeforeEach
            void preprare(){
                insertComment();
                givenComment = commentRepository.selectById(1L).orElseThrow();
            }
            @Test
            @DisplayName("주어진 객체의 content를 변경을 하고, 변경된 객체를 반환한다")
            void it_updates_obj_and_returns_updated_obj(){
                givenComment.changeComment(UPDATE_CONTENT);
                CommentEntity updatedComment = commentRepository.update(givenComment);

                assertAll(
                        () -> assertThat(updatedComment.getId()).isEqualTo(givenComment.getId()),
                        () -> assertThat(updatedComment.getContent()).isEqualTo(UPDATE_CONTENT)
                );
            }
        }
    }

    @Nested
    @DisplayName("delete 메소드는")
    class Describe_delete{

        @BeforeEach
        void preprare(){
            insertComment();
        }

        @Nested
        @DisplayName("저장되어 있는 id가 주어진다면")
        class Context_with_a_saved_id {
            private final Long SAVED_ID = 1L;

            @Test
            @DisplayName("주어진 id에 해당하는 객체를 삭제한다")
            void it_deletes_object(){
                Optional<CommentEntity> selectedComment = commentRepository.selectById(SAVED_ID);
                commentRepository.delete(SAVED_ID);
                Optional<CommentEntity> deletedComment = commentRepository.selectById(SAVED_ID);

                assertAll(
                        () -> assertThat(selectedComment).isPresent(),
                        () -> assertThat(deletedComment).isEmpty()
                );
            }
        }
    }

    @Nested
    @DisplayName("selectByPostId 메소드는")
    class Describe_selectByPostId{

        @BeforeEach
        void prepare(){
            CommentEntity content1 = CommentEntity.builder()
                    .userId(1L)
                    .postId(1L)
                    .content("content1")
                    .build();

            CommentEntity content2 = CommentEntity.builder()
                    .userId(2L)
                    .postId(1L)
                    .content("content2")
                    .build();

            CommentEntity content3 = CommentEntity.builder()
                    .userId(1L)
                    .postId(2L)
                    .content("content3")
                    .build();
            commentRepository.insert(content1);
            commentRepository.insert(content2);
            commentRepository.insert(content3);
        }

        @Nested
        @DisplayName("저장되어 있지 않은 postId가 주어지면")
        class Context_with_a_not_saved_post_id {
            final Long NOT_SAVED_POST_ID = 3L;
            @Test
            @DisplayName("주어진 postId로 조회하고, 비어있는 List를 반환한다")
            void it_finds_comments_by_post_id_and_returns_empty_list(){
                List<CommentEntity> emptyCommentList = commentRepository.selectByPostId(3L);

                assertThat(emptyCommentList)
                        .hasSize(0)
                        .isEmpty();
            }
        }

        @Nested
        @DisplayName("저장되어 있는 postId가 주어지면")
        class Context_with_a_saved_post_id {
            final Long SAVED_POST_ID = 1L;

            @Test
            @DisplayName("주어진 postId로 조회하고, 조회한 객체들을 List로 반환한다")
            void it_finds_comments_by_post_id_and_returns_empty_list(){
                List<CommentEntity> commentList = commentRepository.selectByPostId(SAVED_POST_ID);

                assertThat(commentList)
                        .isNotEmpty()
                        .hasSize(2)
                        .allMatch(comment -> comment.getPostId().equals(SAVED_POST_ID));
            }
        }
    }

    private void insertComment(){
        CommentEntity comment = CommentEntity.builder()
                .userId(1L)
                .postId(1L)
                .content("content")
                .build();

        commentRepository.insert(comment);
    }
}
