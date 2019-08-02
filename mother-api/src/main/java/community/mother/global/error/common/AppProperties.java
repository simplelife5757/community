package community.mother.global.error.common;

import community.mother.domain.model.Password;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties(prefix = "my-app")
@Getter @Setter
public class AppProperties {

	@NotEmpty
	private String adminEmail;

	@NotEmpty
	private String adminNickname;

	@NotEmpty
	private String adminUsername;

	@NotEmpty
	private Password adminPassword;

	@NotEmpty
	private String userEmail;

	@NotEmpty
	private String userNickname;

	@NotEmpty
	private String userUsername;

	@NotEmpty
	private Password userPassword;

	@NotEmpty
	private String clientId;

	@NotEmpty
	private String clientSecret;
}
