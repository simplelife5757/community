package community.mother.domain.post.domain;

import static community.mother.domain.account.domain.AccountTest.getAccountFixture;

public class PostTest {
  public static Post getPostFixture() {
    return Post.builder().title("title")
                    .content("content")
                    .writer(getAccountFixture()).build();
  }
}