package community.mother.domain.account.dto.request;

import community.mother.domain.model.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;

@Getter
@ToString
@NoArgsConstructor
public class SaveAccountParams {

	@Valid
	private Email email;
	private String nickname;
	private String username;
	private String password;

	public SaveAccountParams(Email email, String nickname, String username, String password) {
		this.email = email;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
	}
}
