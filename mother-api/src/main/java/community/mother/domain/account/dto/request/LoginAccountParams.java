package community.mother.domain.account.dto.request;

import community.mother.domain.model.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class LoginAccountParams {
	private Email email;
	private String password;

	public LoginAccountParams(Email email, String password) {
		this.email = email;
		this.password = password;
	}
}
