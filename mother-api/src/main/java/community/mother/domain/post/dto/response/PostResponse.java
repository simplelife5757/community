package community.mother.domain.post.dto.response;

import community.mother.domain.post.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {
	private Long id;
	private String title;
	private String content;
	private Long writerId;
	private String writerNickname;
	private LocalDateTime createdAt;
	private LocalDateTime updateddAt;
	private Long likeCount;
	private Long viewCount;
	private String originalFileName;
	private String savedFileName;

	private PostResponse(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.writerId = post.getWriter().getId();
		this.writerNickname = post.getWriter().getNickname();
		this.createdAt = post.getCreatedAt();
		this.likeCount = post.getLikeCount();
		this.viewCount = post.getViewCount();
//		this.originalFileName = post.getOriginalFileName();
		this.savedFileName = post.getSavedFileName();
	}

	public static PostResponse of(Post post) {
		return new PostResponse(post);
	}
}
