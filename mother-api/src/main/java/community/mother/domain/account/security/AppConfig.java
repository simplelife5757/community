package community.mother.domain.account.security;

import community.mother.domain.account.domain.Account;
import community.mother.domain.account.domain.AccountRole;
import community.mother.domain.account.dto.request.SaveAccountParams;
import community.mother.domain.account.service.AccountService;
import community.mother.global.error.common.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//임의의 유저정보 생성
	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {
			@Autowired
			AccountService accountService;

			@Autowired
			AppProperties appProperties;

			@Override
			public void run(ApplicationArguments args) throws Exception {
				SaveAccountParams adminParams = new SaveAccountParams(
						appProperties.getAdminEmail(),
						appProperties.getAdminNickname(),
						appProperties.getAdminUsername(),
						appProperties.getAdminPassword(),
						Set.of(AccountRole.ADMIN, AccountRole.USER));

				accountService.createAccount(adminParams);

				SaveAccountParams userParams = new SaveAccountParams(
						appProperties.getUserEmail(),
						appProperties.getUserNickname(),
						appProperties.getUserUsername(),
						appProperties.getUserPassword(),
						Set.of(AccountRole.USER));

				accountService.createAccount(userParams);
			}
		};
	}
}
