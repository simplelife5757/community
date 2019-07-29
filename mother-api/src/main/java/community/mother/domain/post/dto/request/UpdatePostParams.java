package community.mother.domain.post.dto.request;

import lombok.Getter;

@Getter
public class UpdatePostParams {
	private String content;

	public UpdatePostParams(String content) {
		this.content = content;
	}
}
