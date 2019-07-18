package community.mother.domain.account.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SaveAccountParams {
	private String email;
	private String nickname;
	private String username;
	private String password;

	public SaveAccountParams(String email, String nickname, String username, String password) {
		this.email = email;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
	}
}
