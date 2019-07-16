package community.mother.account.service;

import community.mother.account.domain.Account;
import community.mother.account.domain.AccountRepository;
import community.mother.account.domain.AccountStatus;
import community.mother.account.dto.request.SaveAccountParams;
import community.mother.account.dto.response.AccountDetail;
import community.mother.account.dto.response.AccountListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	public AccountListResponse getAccounts() {
		return  new AccountListResponse(accountRepository.findAll().stream()
										.map(account -> new AccountDetail(account.getEmail(), account.getNickname()))
										.collect(Collectors.toList()));
	}

	public Long saveAccount(SaveAccountParams accountParams) {
		String encodedPassword = passwordEncoder.encode(accountParams.getPassword());
		LocalDateTime createdAt = LocalDateTime.now();
		AccountStatus userStatus = AccountStatus.CREATED;

		Account account = Account.builder()
				.email(accountParams.getEmail())
				.nickname(accountParams.getNickname())
				.username(accountParams.getUsername())
				.password(encodedPassword)
				.createdAt(createdAt)
				.userStatus(userStatus).build();
		return accountRepository.save(account).getId();
	}
}
