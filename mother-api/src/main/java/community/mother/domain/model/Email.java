package community.mother.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"host", "id"})
public class Email {

	@javax.validation.constraints.Email
	@Column(name = "email", nullable = false, unique = true)
	private String value;

	@Builder
	private Email(String value) {
		this.value = value;
	}

	public static Email of(String email) {
		return new Email(email);
	}
}
