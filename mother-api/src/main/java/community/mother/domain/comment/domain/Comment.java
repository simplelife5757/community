package community.mother.domain.comment.domain;

import community.mother.domain.account.domain.Account;
import community.mother.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "comment")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "COMMENT_ID")
  private Long id;

  @ManyToOne
  @Column(name = "post_id", nullable = false, updatable = false)
  private Post post;

  @ManyToOne
  @Column(name = "user_id", nullable = false, updatable = false)
  private Account account;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private boolean deleted;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  private LocalDateTime deletedAt;

  @Builder
  private Comment(Post post,
                  Account account,
                  String content
  ) {
    Assert.hasLength(content, "Content should not be empty.");

    this.post = post;
    this.account = account;
    this.content = content;
    this.deleted = false;
  }
}
