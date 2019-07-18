package community.mother.global.error.exception;

public class InvalidValueException extends BusinessException{

	public InvalidValueException(final ErrorCode errorCode) {
		super(errorCode);
	}
}
