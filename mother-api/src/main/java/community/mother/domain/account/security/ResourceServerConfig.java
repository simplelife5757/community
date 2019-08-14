package community.mother.domain.account.security;

import community.mother.domain.account.security.social.SocialService;
import community.mother.domain.account.security.social.google.GoogleOAuth2ClientAuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Qualifier("oauth2ClientContext")
	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Autowired
	SocialService socialService;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("event");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
//				.anonymous()
//				.and()
				.authorizeRequests()
//				.mvcMatchers(HttpMethod.GET, "/api/**")
//				.permitAll()
				.antMatchers("/", "/h2-console/**", "/login/**")
				.permitAll()
				.anyRequest().authenticated()
				.and()
//			.csrf()
//				.ignoringAntMatchers("/h2-console/**")
//				.and()
				.headers()
				.frameOptions().disable()
//				.addHeaderWriter(new XFrameOptionsHeaderWriter(new WhiteListedAllowFromStrategy(Collections.singletonList("localhost"))));
				.and()
				.exceptionHandling() //인증이 잘못됐다던가 권한이 없는 경우 발생하는 예외중에 접근권한이 없는 것은
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
				.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
//				.accessDeniedHandler(new OAuth2AccessDeniedHandler()); // OAuth2AccessDeniedHandler 를 사용함 403으로 status 응답을 내보내줌
	}


	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(google(), new GoogleOAuth2ClientAuthenticationProcessingFilter(socialService)));
		filter.setFilters(filters);
		return filter;
	}

	private Filter ssoFilter(ClientResources client, OAuth2ClientAuthenticationProcessingFilter filter) {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(restTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
		filter.setTokenServices(tokenServices);
		tokenServices.setRestTemplate(restTemplate);
		return filter;
	}


	@Bean
	@ConfigurationProperties("google")
	public ClientResources google() {
		return new ClientResources();
	}
}
