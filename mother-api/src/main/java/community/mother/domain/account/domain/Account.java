package community.mother.domain.account.domain;

import community.mother.domain.model.Password;
import community.mother.domain.post.domain.Post;
import community.mother.domain.post.dto.request.UpdatePostParams;
import community.mother.domain.post.exception.PostNotFoundException;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.LocalDateTime.now;

@Getter
@Table(name = "account")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String username;

	private Password password;

	@OneToMany(mappedBy = "writer", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();

	private String website;
	private String description;
	private String phone;
	private String gender;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<AccountRole> roles;

	@ManyToMany
	@JoinTable(name = "follow", joinColumns = @JoinColumn(name = "following", referencedColumnName = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "followed", referencedColumnName = "account_id"))
	public Set<Account> followingAccounts = new HashSet<>();

	@ManyToMany(mappedBy = "followingAccounts")
	public Set<Account> followedAccounts = new HashSet<>();

	@Builder
	private Account(String email,
				   String nickname,
				   String username,
				   Password password,
				Set<AccountRole> roles
	) {
		Assert.hasLength(email, "email should not be empty.");
		Assert.hasLength(nickname, "nickname should not be empty.");
		Assert.hasLength(username, "username should not be empty.");
//		Assert.hasLength(password, "password should not be empty.");

		this.email = email;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
//		this.createdAt = now();
		this.accountStatus = AccountStatus.CREATED;
		this.roles = roles;
	}
//
//	public boolean matchPassword(String password, PasswordEncoder passwordEncoder) {
//		return this.password.isMatched(password;
//	}

	public void update(String username, String nickname, String website, String description, String email, String phone, String gender) {
		this.username = username;
		this.nickname = nickname;
		this.website = website;
		this.description = description;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
//		this.updatedAt = now();
	}

	public void delete() {
		this.accountStatus = AccountStatus.DELETED;
		this.deletedAt = now();
	}

	public boolean isDeleted() {
		return this.accountStatus == AccountStatus.DELETED;
	}

	public Set<AccountRole> getRole() {
		return roles;
	}

	public void addPost(Post post) {
		posts.add(0, post);
	}

	public void updatePost(Long postId, UpdatePostParams updatePostParams) {
		Post post = getPostById(postId);
		post.update(updatePostParams.getContent());
	}

	private Post getPostById(Long id) {
		for (Post post: this.posts) {
			if (post.has(id)) {
				return post;
			}
		} throw new PostNotFoundException();
	}

	public void deletePost(Post post) {
		this.posts.remove(post);
	}

	public void addFollowingAccount(Account followingAccount) {
		this.followingAccounts.add(followingAccount);
		followingAccount.addFollowedAccount(this);
	}

	private void addFollowedAccount(Account followedAccount) {
		this.followedAccounts.add(followedAccount);
	}

	public void deleteFollowingAccount(Account followingAccount) {
		this.followingAccounts.remove(followingAccount);
		followingAccount.deleteFollowedAccount(this);
	}

	private void deleteFollowedAccount(Account followingAccount) {
		this.followingAccounts.remove(followingAccount);
	}
}
