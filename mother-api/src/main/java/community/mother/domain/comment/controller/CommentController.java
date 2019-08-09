package community.mother.domain.comment.controller;

import community.mother.domain.comment.dto.request.SaveCommentParams;
import community.mother.domain.comment.dto.request.UpdateCommentParams;
import community.mother.domain.comment.dto.response.CommentResponse;
import community.mother.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
  private final CommentService commentService;

  @PostMapping
  public Long create(@RequestBody @Valid SaveCommentParams commentParams) {
    return commentService.createComment(commentParams);
  }

  @GetMapping("/{id}")
  public CommentResponse read(@PathVariable Long id) {
    return commentService.readComment(id);
  }

  @PostMapping("/{id}")
  public void update(@PathVariable Long id, @RequestBody @Valid UpdateCommentParams commentParams) {
    commentService.updateComment(id, commentParams);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    commentService.deleteComment(id);
  }
}
