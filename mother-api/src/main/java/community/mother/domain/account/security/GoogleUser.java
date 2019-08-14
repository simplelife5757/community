package community.mother.domain.account.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import community.mother.domain.account.security.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jojoldu@gmail.com on 2017. 8. 13.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUser implements Serializable {

  @JsonProperty("email")
  private String email;
  @JsonProperty("name")
  private String name;
  @JsonProperty("picture")
  private String picture;

  public User toEntity(){
    return User.builder()
        .email(email)
        .name(name)
        .imageUrl(picture)
        .build();
  }
}