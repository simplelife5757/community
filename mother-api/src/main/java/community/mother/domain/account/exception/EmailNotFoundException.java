package community.mother.domain.account.exception;

import community.mother.global.error.exception.EntityNotFoundException;
import community.mother.global.error.exception.ErrorCode;

public class EmailNotFoundException extends EntityNotFoundException {
	public EmailNotFoundException() {
		super(ErrorCode.EMAIL_EMPTY);
	}
}
