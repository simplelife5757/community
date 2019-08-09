package community.mother.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class UpdateCommentParams {
  private @NotEmpty String content;
}
