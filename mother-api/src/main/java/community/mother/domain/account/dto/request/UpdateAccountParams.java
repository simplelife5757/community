package community.mother.domain.account.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UpdateAccountParams {
	private String username;
	private String nickname;
	private String website;
	private String description;
	private String email;
	private String phone;
	private String gender;

	public UpdateAccountParams(String username, String nickname, String website, String description, String email, String phone, String gender) {
		this.username = username;
		this.nickname = nickname;
		this.website = website;
		this.description = description;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
	}
}
