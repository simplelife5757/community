package community.mother.domain.account.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

// SpringSecurity User를 Account 객체를 알고 있는 객체로 바꾸는 어댑터 클래스
public class AccountAdapter extends User {

	public Long accountId;

	public AccountAdapter(Account account) {
		super(account.getUsername(), account.getPassword(), authorities(account.getRole()));
		this.accountId = account.getId();
	}

	private static Collection<? extends GrantedAuthority> authorities(Set<AccountRole> roles) {
		return roles.stream()
				.map(r -> new SimpleGrantedAuthority("ROLE+" + r.name()))
				.collect(Collectors.toSet());
	}

	public Long getAccount() {
		return accountId;
	}
}
