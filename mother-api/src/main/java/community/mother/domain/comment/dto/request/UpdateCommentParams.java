package community.mother.domain.comment.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UpdateCommentParams {
  private @NotEmpty String content;
}
