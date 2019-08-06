package community.mother.domain.comment.dto.response;

import community.mother.domain.comment.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
  private Long id;
  private Long postId;
  private String writer;
  private String content;
  private LocalDateTime createdAt;

  private CommentResponse(Comment comment) {
    this.id = comment.getId();
    this.postId = comment.getPost().getId();
    this.writer = comment.getWriter().getNickname();
    this.content = comment.getContent();
    this.createdAt = comment.getCreatedAt();
  }

  public static CommentResponse of(Comment comment) {
    return new CommentResponse(comment);
  }
}
