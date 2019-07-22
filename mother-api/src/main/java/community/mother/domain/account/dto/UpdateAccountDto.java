package community.mother.domain.account.dto;

import lombok.Getter;

@Getter
public class UpdateAccountDto {
	private String username;
	private String nickname;
	private String website;
	private String description;
	private String email;
	private String phone;
	private String gender;

	public UpdateAccountDto(String username, String nickname, String website, String description, String email, String phone, String gender) {
		this.username = username;
		this.nickname = nickname;
		this.website = website;
		this.description = description;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
	}
}
