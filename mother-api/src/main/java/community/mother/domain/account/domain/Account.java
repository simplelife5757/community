package community.mother.domain.account.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(nullable = false)
	private String email;

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

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountStatus userStatus;

	@Builder
	public Account(String email,
				   String nickname,
				   String username,
				   String password,
				   String website,
				   String description,
				   String phone,
				   String gender,
				   LocalDateTime createdAt,
				   LocalDateTime updatedAt,
				   AccountStatus userStatus
	) {
		Assert.hasLength(email, "email should not be empty.");
		Assert.hasLength(nickname, "nickname should not be empty.");
		Assert.hasLength(username, "username should not be empty.");
		Assert.hasLength(password, "password should not be empty.");

		this.email = email;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.website = website;
		this.description = description;
		this.phone = phone;
		this.gender = gender;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userStatus = userStatus;
	}

	public boolean matchPassword(String password, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(password, this.password);
	}
}
