package community.mother.domain.account.controller;

import community.mother.domain.account.dto.request.LoginAccountParams;
import community.mother.domain.account.dto.request.SaveAccountParams;
import community.mother.domain.account.dto.response.AccountListResponse;
import community.mother.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountRestController {
	private final AccountService accountService;

	@GetMapping
	public AccountListResponse getAccounts() {
		return accountService.getAccounts();
	}

	@PostMapping
	public void saveAccount(@RequestBody SaveAccountParams accountParams) {
		accountService.saveAccount(accountParams);
	}

	@PostMapping("/login")
	public void login(@RequestBody LoginAccountParams accountParams, HttpSession session) {
		accountService.login(accountParams, session);
	}
}
