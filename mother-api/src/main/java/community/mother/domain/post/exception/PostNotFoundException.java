package community.mother.domain.post.exception;

import community.mother.global.error.exception.EntityNotFoundException;
import community.mother.global.error.exception.ErrorCode;

public class PostNotFoundException extends EntityNotFoundException {
	public PostNotFoundException() {
		super(ErrorCode.POST_EMPTY);
	}
}
