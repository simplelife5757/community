package community.mother.domain.account.exception;

import community.mother.global.error.exception.ErrorCode;
import community.mother.global.error.exception.InvalidValueException;

public class PasswordMismatchException extends InvalidValueException {
	public PasswordMismatchException() {
		super(ErrorCode.PASSWORD_MISMATCH);
	}
}
