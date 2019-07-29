package community.mother.domain.account.exception;

import community.mother.global.error.exception.EntityNotFoundException;
import community.mother.global.error.exception.ErrorCode;

public class AccountNotFoundException extends EntityNotFoundException {
	public AccountNotFoundException() {
		super(ErrorCode.ACCOUNT_EMPTY);
	}
}
