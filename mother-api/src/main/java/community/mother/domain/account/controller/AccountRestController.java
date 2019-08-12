package community.mother.domain.account.controller;

import community.mother.domain.account.domain.CurrentUser;
import community.mother.domain.account.dto.request.UpdateAccountParams;
import community.mother.domain.account.dto.request.SaveAccountParams;
import community.mother.domain.account.dto.response.AccountListResponse;
import community.mother.domain.account.service.AccountService;
import community.mother.domain.account.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountRestController {
	private final AccountService accountService;
	private final FollowService followService;

	@GetMapping
	public AccountListResponse getAccounts() {
		return accountService.getAccounts();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Long create(@RequestBody @Valid SaveAccountParams accountParams) {
		return accountService.createAccount(accountParams);
	}

	@PostMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody UpdateAccountParams accountParams) {
		accountService.updateAccount(id, accountParams);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		accountService.deleteAccount(id);
	}

	@GetMapping("/{accountNickname}/follows")
	public void getFollows(@PathVariable String accountNickname) {
		followService.getFollows(accountNickname);
	}

	@GetMapping("/{accountNickname}/followed-by")
	public void getFollowed(@PathVariable String accountNickname) {
		followService.getFollowed(accountNickname);
	}

	@PostMapping("/self/follows/{accountNickname}")
	public void createFollow(@PathVariable String accountNickname, @CurrentUser Long accountId) {
		followService.createFollow(accountNickname, accountId);
	}

	@DeleteMapping("/self/follows/{accountNickname}")
	public void deleteFollow(@PathVariable String accountNickname, @CurrentUser Long accountId) {
		followService.deleteFollow(accountNickname, accountId);
	}
}
