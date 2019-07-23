package community.mother.domain.account.controller;

import community.mother.domain.account.dto.request.UpdateAccountParams;
import community.mother.domain.account.dto.request.LoginAccountParams;
import community.mother.domain.account.dto.request.SaveAccountParams;
import community.mother.domain.account.dto.response.AccountListResponse;
import community.mother.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
	@ResponseStatus(value = HttpStatus.CREATED)
	public Long create(@RequestBody @Valid SaveAccountParams accountParams) {
		return accountService.createAccount(accountParams);
	}

	@PostMapping("/login")
	public Long login(@RequestBody LoginAccountParams accountParams, HttpSession session) {
		return accountService.login(accountParams, session);
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
