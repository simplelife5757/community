package community.mother.domain.comment.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class SaveCommentParams {
  private @NotNull Long postId;
  private @NotEmpty String content;
}
