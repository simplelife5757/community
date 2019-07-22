package community.mother.domain.account.service;

import community.mother.domain.account.domain.Account;
import community.mother.domain.account.domain.AccountRepository;
import community.mother.domain.account.dto.request.LoginAccountParams;
import community.mother.domain.account.dto.request.SaveAccountParams;
import community.mother.domain.account.dto.request.UpdateAccountParams;
import community.mother.domain.account.dto.response.AccountDetail;
import community.mother.domain.account.dto.response.AccountListResponse;
import community.mother.domain.account.exception.AccountNotFoundException;
import community.mother.domain.account.exception.EmailNotFoundException;
import community.mother.domain.account.exception.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

	public Long createAccount(SaveAccountParams accountParams) {
		String encodedPassword = passwordEncoder.encode(accountParams.getPassword());

		Account account = Account.builder()
				.email(accountParams.getEmail())
				.nickname(accountParams.getNickname())
				.username(accountParams.getUsername())
				.password(encodedPassword)
				.build();
		return accountRepository.save(account).getId();
	}

	public void login(LoginAccountParams accountParams, HttpSession session) {
		String email = accountParams.getEmail();
		Account account = accountRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
		if (account.matchPassword(accountParams.getPassword(), passwordEncoder)) {
			log.info("login success! accountParams={}", accountParams);
			session.setAttribute("LOGIN_ACCOUNT", account);
			return;
		}
		throw new PasswordMismatchException();
	}

	public void updateAccount(Long id, UpdateAccountParams accountParams) {
		Account account = findAccountById(id);
		account.update(accountParams.getUsername(), accountParams.getNickname(), accountParams.getWebsite(), accountParams.getDescription()
				, accountParams.getEmail(), accountParams.getPhone(), accountParams.getGender());
		accountRepository.save(account);
	}

	public void deleteAccount(Long id) {
		Account account = findAccountById(id);
		account.delete();
		accountRepository.save(account);
	}

	private Account findAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);

		if(account.isDeleted()) {
			throw new AccountNotFoundException();
		}

		return account;
	}
}
