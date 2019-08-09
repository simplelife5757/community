package community.mother.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class SaveCommentParams {
  private @NotNull Long postId;
  private @NotEmpty String content;
}
