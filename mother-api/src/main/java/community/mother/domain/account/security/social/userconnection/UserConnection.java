package community.mother.domain.account.security.social.userconnection;

import community.mother.domain.account.security.social.google.GoogleUserDetails;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_connection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserConnection {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "email")
  private String email;

  @Column(name = "provider")
  @Enumerated(EnumType.STRING)
  private ProviderType provider;

  @Column(name = "provider_id", unique = true, nullable = false)
  private String providerId;

  @Column(name = "display_name")
  private String displayName;

  @Column(name = "profile_url")
  private String profileUrl;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "access_token")
  private String accessToken;


  @Column(name = "expire_time")
  private long expireTime;

  @Builder
  private UserConnection(String email, ProviderType provider, String providerId, String displayName, String profileUrl, String imageUrl, String accessToken, long expireTime) {
    this.email = email;
    this.provider = provider;
    this.providerId = providerId;
    this.displayName = displayName;
    this.profileUrl = profileUrl;
    this.imageUrl = imageUrl;
    this.accessToken = accessToken;
    this.expireTime = expireTime;
  }

  public static UserConnection valueOf(GoogleUserDetails userDetails) {
    return UserConnection.builder()
        .expireTime(userDetails.getExpiration())
        .accessToken(userDetails.getAccess_token())
        .providerId(userDetails.getSub())
        .email(userDetails.getEmail())
        .displayName(userDetails.getName())
        .imageUrl(userDetails.getPicture())
        .provider(ProviderType.GOOGLE)
        .profileUrl(userDetails.getProfile())
        .build();
  }


}