package community.mother.domain.post.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import community.mother.domain.account.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "post")
@Entity
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	private Long id;

	@Column(nullable = false)
	private String writer;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false, updatable = false)
	private Account account;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updateddAt;

	@Column(nullable = false)
	private int likeCount;

	@Column(nullable = false)
	private int viewCount;

	private String originalFileName;

	private String savedFileName;

	@Builder
	private Post(String writer,
				 String title,
				 String content,
				 String originalFileName,
				 String savedFileName
	) {
		Assert.hasLength(writer, "writer should not be empty.");
		Assert.hasLength(title, "title should not be empty.");
		Assert.hasLength(content, "content should not be empty.");

		this.writer = writer;
		this.title = title;
		this.content = content;
		this.originalFileName = originalFileName;
		this.savedFileName = savedFileName;
	}
}
