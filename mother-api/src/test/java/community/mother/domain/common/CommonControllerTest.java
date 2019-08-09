package community.mother.domain.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import community.mother.global.error.common.AppProperties;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CommonControllerTest {
  public @Autowired MockMvc mvc;
  public @Autowired ObjectMapper objectMapper;
  public @Autowired AppProperties appProperties;
  protected String bearerToken;

  @BeforeEach
  protected void setUp() throws Exception{
    bearerToken = getBearerToken();
  }

  private String getBearerToken() throws Exception {
    return "Bearer " + getAccessToken();
  }

  private String getAccessToken() throws Exception {
    ResultActions perform = mvc.perform(post("/oauth/token")
        .with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret())) // Basic OAuth Header
        .param("username", "user@email.com")
        .param("password", "userPassword")
        .param("grant_type", "password"));
    var responseBody = perform.andReturn().getResponse().getContentAsString();
    Jackson2JsonParser parser = new Jackson2JsonParser();
    return parser.parseMap(responseBody).get("access_token").toString();
  }
}
