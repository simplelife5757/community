package community.mother.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
	// Common
	INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
	ENTITY_NOT_FOUND(400, "C002", "Entity Not Found"),

	// Account
	EMAIL_EMPTY(400, "A001", "Email is Not Found"),
	EMAIL_DUPLICATION(400, "A002", "Email is Duplication"),
	PASSWORD_MISMATCH(400, "A003", "Password is Mismatch");

	private final String code;
	private final String message;
	private int status;

	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public int getStatus() {
		return this.status;
	}
}
