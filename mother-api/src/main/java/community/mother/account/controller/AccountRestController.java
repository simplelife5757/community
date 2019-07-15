package community.mother.account.controller;

import community.mother.account.dto.request.SaveAccountParams;
import community.mother.account.dto.response.AccountListResponse;
import community.mother.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
	public void saveAccount(@RequestBody SaveAccountParams saveAccountParams) {
		accountService.saveAccount(saveAccountParams);
	}
}
