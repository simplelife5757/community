package community.mother.account.service;

import community.mother.account.domain.Account;
import community.mother.account.domain.AccountRepository;
import community.mother.account.domain.Status;
import community.mother.account.dto.request.LoginAccountParams;
import community.mother.account.dto.request.SaveAccountParams;
import community.mother.account.dto.response.AccountDetail;
import community.mother.account.dto.response.AccountListResponse;
import community.mother.account.exception.AccountNotFoundException;
import community.mother.account.exception.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	public Long saveAccount(SaveAccountParams saveAccountParams) {
		String encodedPassword = passwordEncoder.encode(saveAccountParams.getPassword());
		LocalDateTime createdAt = LocalDateTime.now();
		Status userStatus = Status.CREATED;

		Account account = Account.builder()
				.email(saveAccountParams.getEmail())
				.nickname(saveAccountParams.getNickname())
				.username(saveAccountParams.getUsername())
				.password(encodedPassword)
				.createdAt(createdAt)
				.userStatus(userStatus).build();
		return accountRepository.save(account).getId();
	}

	public AccountListResponse getAccounts() {
		return new AccountListResponse(accountRepository.findAll().stream()
						.map(account -> new AccountDetail(account.getEmail(), account.getNickname()))
						.collect(Collectors.toList()));
	}

	public void login(LoginAccountParams accountParams, HttpSession session) {
		String email = accountParams.getEmail();
		Account account = accountRepository.findByEmail(email).orElseThrow(AccountNotFoundException::new);
		if (account.mathPassword(accountParams.getPassword(), passwordEncoder)) {
			log.info("로그인 성공! accountParams={}", accountParams);
			session.setAttribute("LOGIN_ACCOUNT", account);
			return;
		}
		throw new PasswordMismatchException();
	}

}
