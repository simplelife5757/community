package community.mother.domain.account.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("event");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.anonymous()
				.and()
			.authorizeRequests()
				.mvcMatchers(HttpMethod.GET, "/api/**")
					.permitAll()
				.antMatchers("/h2-console/**")
					.permitAll()
				.anyRequest().authenticated()
				.and()
			.headers()
			.frameOptions().disable()
				.and()
			.exceptionHandling() //인증이 잘못됐다던가 권한이 없는 경우 발생하는 예외중에 접근권한이 없는 것은
				.accessDeniedHandler(new OAuth2AccessDeniedHandler()); // OAuth2AccessDeniedHandler 를 사용함 403으로 status 응답을 내보내줌
	}
}
