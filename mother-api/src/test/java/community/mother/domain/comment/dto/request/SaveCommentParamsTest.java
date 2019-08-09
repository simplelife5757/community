package community.mother.domain.comment.dto.request;

import static org.junit.jupiter.api.Assertions.*;

public class SaveCommentParamsTest {
  public static SaveCommentParams getSaveCommentParamsFixture() {
    return new SaveCommentParams(1L, "content");
  }

  public static SaveCommentParams getSaveCommentNullFieldsParamsFixture() {
    return new SaveCommentParams(1L, "");
  }
}