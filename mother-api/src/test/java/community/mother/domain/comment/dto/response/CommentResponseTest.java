package community.mother.domain.comment.dto.response;

import community.mother.domain.comment.domain.Comment;
import org.junit.jupiter.api.Test;

import static community.mother.domain.comment.domain.CommentTest.getCommentFixture;
import static org.assertj.core.api.BDDAssertions.then;

public class CommentResponseTest {
  public static CommentResponse getCommentResponseFixture() {
    return CommentResponse.of(getCommentFixture());
  }

  @Test
  void construct_ValidInput_ValidOutput() {
    Comment comment = getCommentFixture();
    CommentResponse commentResponse = CommentResponse.of(comment);

    then(commentResponse)
        .hasFieldOrPropertyWithValue("id", comment.getId())
        .hasFieldOrPropertyWithValue("postId", comment.getPost().getId())
        .hasFieldOrPropertyWithValue("writer", comment.getWriter().getNickname())
        .hasFieldOrPropertyWithValue("content", comment.getContent())
        .hasFieldOrPropertyWithValue("createdAt", comment.getCreatedAt());
  }
}