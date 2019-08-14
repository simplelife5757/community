package community.mother.domain.account.security;

import community.mother.domain.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

//SpringSecurity 설정
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AccountService accountService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
//		유저 인증정보를 가지고 있음
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
//				.userDetailsService(accountService)
				.passwordEncoder(passwordEncoder)
				.withUser("user@email.com")
				.password(passwordEncoder.encode("userPassword"))
				.roles("USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/docs/index.html");
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

}
