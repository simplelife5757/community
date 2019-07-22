package community.mother.domain.account.controller;

import community.mother.domain.account.dto.request.UpdateAccountParams;
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
	public void create(@RequestBody SaveAccountParams accountParams) {
		accountService.createAccount(accountParams);
	}

	@PostMapping("/login")
	public void login(@RequestBody LoginAccountParams accountParams, HttpSession session) {
		accountService.login(accountParams, session);
	}

	@PostMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody UpdateAccountParams accountParams) {
		accountService.updateAccount(id, accountParams);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		accountService.deleteAccount(id);
	}
}
