package community.mother.domain.post.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import community.mother.domain.account.domain.Account;
import community.mother.domain.post.dto.request.UpdatePostParams;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = "id")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false, updatable = false)
	private Account writer;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updateddAt;

	private Long likeCount;

	private Long viewCount;

	private String originalFileName;

	private String savedFileName;

	@Builder
	private Post(String title,
				 String content,
				 Account writer,
				 String originalFileName,
				 String savedFileName
	) {
		Assert.hasLength(title, "title should not be empty.");
		Assert.hasLength(content, "content should not be empty.");

		this.title = title;
		this.content = content;
		this.writer = writer;
		this.originalFileName = originalFileName;
		this.savedFileName = savedFileName;
	}

	public void update(String content) {
		this.content = content;
	}

	public boolean isWriter(Account account) {
		return this.writer.equals(account);
	}

	public boolean has(Long id) {
		return this.id.equals(id);
	}
}
