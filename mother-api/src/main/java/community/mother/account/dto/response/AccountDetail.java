package community.mother.account.dto.response;

import lombok.Getter;

@Getter
public class AccountDetail {
	private String email;
	private String nickname;

	public AccountDetail(String email, String nickname) {
		this.email = email;
		this.nickname = nickname;
	}
}
