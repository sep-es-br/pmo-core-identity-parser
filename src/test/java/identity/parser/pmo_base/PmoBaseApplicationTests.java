package identity.parser.pmo_base;

import br.gov.es.pmo.identity_parser.pmo_base.PmoBaseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

@SpringBootTest(classes = PmoBaseApplication.class)
class PmoBaseApplicationTests {
    
    @MockBean
    private OAuth2AuthorizedClientService OAuth2AuthorizedClientService;
    
    @MockBean
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Test
    void contextLoads() {
    }

}
