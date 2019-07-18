package community.mother.domain.account.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class AccountListResponse {
	private List<AccountDetail> accounts;

	public AccountListResponse(List<AccountDetail> accounts) {
		this.accounts = accounts;
	}
}
