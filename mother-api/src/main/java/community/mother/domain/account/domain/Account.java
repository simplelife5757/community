package community.mother.domain.account.domain;

import community.mother.domain.model.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@Entity
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Embedded
	private Email email;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
	private String website;
	private String description;
	private String phone;
	private String gender;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;

	@Builder
	public Account(Email email,
				   String nickname,
				   String username,
				   String password
	) {
//		Assert.hasLength(email, "email should not be empty.");
		Assert.hasLength(nickname, "nickname should not be empty.");
		Assert.hasLength(username, "username should not be empty.");
		Assert.hasLength(password, "password should not be empty.");

		this.email = email;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.createdAt = now();
		this.accountStatus = AccountStatus.CREATED;
	}

	public boolean matchPassword(String password, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(password, this.password);
	}

	public void update(String username, String nickname, String website, String description, Email email, String phone, String gender) {
		this.username = username;
		this.nickname = nickname;
		this.website = website;
		this.description = description;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.updatedAt = now();
	}

	public void delete() {
		this.accountStatus = AccountStatus.DELETED;
		this.deletedAt = now();
	}

	public boolean isDeleted() {
		return this.accountStatus == AccountStatus.DELETED;
	}
}
