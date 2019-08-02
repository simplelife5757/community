package community.mother.domain.model;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
  @Column(name = "password", nullable = false)
  private String value;

  @Column(name = "password_expiration_date")
  private LocalDateTime expirationDate;

  @Column(name = "password_ttl")
  private long ttl;

  private Password(final String value) {
    this.ttl = 1209_604; // 1209_604 is 14 days
    this.value = encodePassword(value);
  }

  public static Password of(String value) {
    return new Password(value);
  }

  private String encodePassword(String value) {
    return new BCryptPasswordEncoder().encode(value);
  }
}
