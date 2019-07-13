package community.mother.account.controller;

import community.mother.account.dto.request.LoginAccountParams;
import community.mother.account.dto.request.SaveAccountParams;
import community.mother.account.dto.response.AccountListResponse;
import community.mother.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
		log.info("save account request accountParams={}", accountParams);
		accountService.saveAccount(accountParams);
	}

	@PostMapping("/login")
	public void login(@RequestBody LoginAccountParams accountParams, HttpSession session) {
		log.info("login accountParams={}", accountParams);
		accountService.login(accountParams, session);
	}

	@GetMapping("/login")
	public ResponseEntity login(HttpSession session) {
//		Cookie cookie = new Cookie("username", "jordan");
//		httpServletResponse.addCookie(cookie);
		LoginAccountParams accountParams = (LoginAccountParams) session.getAttribute("LOGIN_ACCOUNT");
		accountParams.
		log.info("login");
		return ResponseEntity.ok().body("str");
	}
}
