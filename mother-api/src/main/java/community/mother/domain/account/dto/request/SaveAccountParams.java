package community.mother.domain.account.dto.request;

import community.mother.domain.account.domain.AccountRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveAccountParams {

	@Email @NotEmpty
	private String email;
	private @NotEmpty String nickname;
	private @NotEmpty String username;
	private @NotEmpty String password;
	private Set<AccountRole> roles = new HashSet<>();

	public SaveAccountParams(String email, String nickname, String username, String password) {
		this.email = email;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
	}
}
