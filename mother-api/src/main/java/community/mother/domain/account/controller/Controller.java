package community.mother.domain.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class Controller {
  @GetMapping("/me")
  public Principal home(Principal principal) {
    return principal;
  }
}
