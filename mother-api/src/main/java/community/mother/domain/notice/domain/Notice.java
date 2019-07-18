package community.mother.domain.notice.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;

	@Builder
	public Notice(
			String title,
			String content
	) {
		Assert.hasLength(title, "title should not be empty.");
		Assert.hasLength(content, "content should not be empty.");

		this.title = title;
		this.content = content;
	}
}
