package community.mother.domain.comment.controller;

import community.mother.domain.comment.dto.request.SaveCommentParams;
import community.mother.domain.comment.dto.request.UpdateCommentParams;
import community.mother.domain.comment.dto.response.CommentResponse;
import community.mother.domain.comment.service.CommentService;
import community.mother.domain.common.CommonControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static community.mother.domain.comment.dto.request.SaveCommentParamsTest.getSaveCommentParamsFixture;
import static community.mother.domain.comment.dto.request.UpdateCommentParamsTest.getUpdateCommentParamsFixture;
import static community.mother.domain.comment.dto.response.CommentResponseTest.getCommentResponseFixture;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerTest extends CommonControllerTest {
  private @MockBean CommentService commentService;

  @Test
  void createComment_ValidInput_ValidOutput() throws Exception {
    // given
    SaveCommentParams commentParams = getSaveCommentParamsFixture();
    given(commentService.createComment(any(SaveCommentParams.class))).willReturn(1L);

    // expect
    this.mvc.perform(post("/api/comments")
      .header(HttpHeaders.AUTHORIZATION, bearerToken)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(commentParams)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$").value(1L));
  }

  @Test
  void readComment_ValidInput_CommentResponse() throws Exception {
    // given
    CommentResponse commentResponse = getCommentResponseFixture();
    given(commentService.readComment(anyLong())).willReturn(commentResponse);

    // expect
    mvc.perform(get("/api/comments/{id}", 1L)
      .header(HttpHeaders.AUTHORIZATION, bearerToken))
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").value(commentResponse.getId()))
      .andExpect(jsonPath("postId").value(commentResponse.getPostId()))
      .andExpect(jsonPath("writer").value(commentResponse.getWriter()))
      .andExpect(jsonPath("content").value(commentResponse.getContent()));
  }

  @Test
  void updateComment_ValidInput_ValidOutput() throws Exception {
    // given
    UpdateCommentParams commentParams = getUpdateCommentParamsFixture();

    // expect
    this.mvc.perform(post("/api/comments/{id}", 1L)
      .header(HttpHeaders.AUTHORIZATION, bearerToken)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(commentParams)))
      .andExpect(status().isOk());
  }

  @Test
  void deleteComment_ValidInput_ValidOutput() throws Exception {
    this.mvc.perform(delete("/api/comments/{id}", 1L)
        .header(HttpHeaders.AUTHORIZATION, bearerToken))
        .andExpect(status().isOk());
  }
}