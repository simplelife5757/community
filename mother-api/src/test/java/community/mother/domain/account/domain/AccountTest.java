package community.mother.domain.account.domain;

import java.util.Set;

import static community.mother.domain.model.PasswordTest.getPasswordFixture;

public class AccountTest {
  public static Account getAccountFixture() {
    return Account.builder().email("jiwoo627@naver.com")
                      .nickname("simple5757")
                      .username("jiwoo")
                      .password(getPasswordFixture())
                      .roles(Set.of(AccountRole.USER)).build();
  }

}