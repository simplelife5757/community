package community.mother.domain.account.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class LoginAccountParams {
	private String email;
	private String password;

	public LoginAccountParams(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
