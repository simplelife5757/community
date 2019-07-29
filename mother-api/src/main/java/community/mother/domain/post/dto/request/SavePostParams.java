package community.mother.domain.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SavePostParams {
	private String title;
	private String content;
	private String originalFileName;
}
