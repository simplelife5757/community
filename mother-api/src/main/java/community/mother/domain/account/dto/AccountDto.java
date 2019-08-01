package community.mother.domain.account.dto;

import community.mother.domain.account.domain.AccountRole;
import community.mother.domain.account.domain.AccountStatus;
import community.mother.domain.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class AccountDto {
	private String email;
	private String nickname;
	private String username;
	private String password;
	private List<Post> posts;
	private String website;
	private String description;
	private String phone;
	private String gender;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	private AccountStatus accountStatus;
	private Set<AccountRole> roles;
}
