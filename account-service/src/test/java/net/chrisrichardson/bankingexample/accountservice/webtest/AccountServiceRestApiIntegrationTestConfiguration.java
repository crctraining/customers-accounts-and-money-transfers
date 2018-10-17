package net.chrisrichardson.bankingexample.accountservice.webtest;

import io.eventuate.tram.inmemory.TramInMemoryConfiguration;
import net.chrisrichardson.bankingexample.accountservice.web.AccountWebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({AccountWebConfiguration.class, TramInMemoryConfiguration.class})
@EnableAutoConfiguration
public class AccountServiceRestApiIntegrationTestConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
