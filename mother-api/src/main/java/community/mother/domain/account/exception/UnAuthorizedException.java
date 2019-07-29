package community.mother.domain.account.exception;

import community.mother.global.error.exception.BusinessException;
import community.mother.global.error.exception.ErrorCode;

public class UnAuthorizedException extends BusinessException {
  public UnAuthorizedException() {
    super(ErrorCode.ACCOUNT_UNAUTHORIZATION);
  }
}
