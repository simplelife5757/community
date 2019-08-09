package community.mother.domain.comment.service;

import community.mother.domain.comment.domain.Comment;
import community.mother.domain.comment.dto.request.SaveCommentParams;
import community.mother.domain.comment.dto.request.UpdateCommentParams;
import community.mother.domain.comment.dto.response.CommentResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  public Long createComment(SaveCommentParams commentParams) {
    return 1L;
  }

  public CommentResponse readComment(Long id) {
    return CommentResponse.of(Comment.builder().content("content").build());
  }

  public void updateComment(Long id, UpdateCommentParams commentParams) {
  }

  public void deleteComment(Long id) {
  }
}
