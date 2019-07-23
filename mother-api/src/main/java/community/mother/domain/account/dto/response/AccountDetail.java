package community.mother.domain.account.dto.response;

import community.mother.domain.model.Email;
import lombok.Getter;

@Getter
public class AccountDetail {
	private Email email;
	private String nickname;

	public AccountDetail(Email email, String nickname) {
		this.email = email;
		this.nickname = nickname;
	}
}
