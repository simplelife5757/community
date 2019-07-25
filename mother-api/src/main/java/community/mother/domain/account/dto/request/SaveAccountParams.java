package community.mother.domain.account.dto.request;

import community.mother.domain.account.domain.AccountRole;
import community.mother.domain.model.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveAccountParams {

	@Valid
	private Email email;
	private String nickname;
	private String username;
	private String password;
	private Set<AccountRole> roles = new HashSet<>();

	public SaveAccountParams(Email email, String nickname, String username, String password) {
		this.email = email;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
	}
}
