package community.mother.domain.comment.domain;

import org.junit.jupiter.api.Test;

import static community.mother.domain.account.domain.AccountTest.getAccountFixture;
import static community.mother.domain.post.domain.PostTest.getPostFixture;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class CommentTest {
  public static Comment getCommentFixture() {
    return Comment.builder()
                          .post(getPostFixture())
                          .account(getAccountFixture())
                          .content("content").build();
  }

  @Test
  void buildComment_ValidInput_CreatedComment() {
    Comment comment = getCommentFixture();

    then(comment).hasFieldOrPropertyWithValue("post", comment.getPost())
        .hasFieldOrPropertyWithValue("account", comment.getAccount())
        .hasFieldOrPropertyWithValue("content", "content")
        .hasFieldOrPropertyWithValue("deleted", false)
        .hasFieldOrPropertyWithValue("createdAt", comment.getCreatedAt());
  }

  @Test
  void build_EmptyContent_ThrowException() {
    thenThrownBy(() ->
        Comment.builder()
            .content("").build()
    ).isInstanceOf(IllegalArgumentException.class);
  }
}