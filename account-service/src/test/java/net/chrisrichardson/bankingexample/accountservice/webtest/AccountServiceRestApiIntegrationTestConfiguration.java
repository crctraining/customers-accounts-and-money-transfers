package net.chrisrichardson.bankingexample.accountservice.webtest;

import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import net.chrisrichardson.bankingexample.accountservice.backend.MockPassengerRepositoryConfiguration;
import net.chrisrichardson.bankingexample.accountservice.web.AccountWebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({AccountWebConfiguration.class, MockPassengerRepositoryConfiguration.class,
        EmbeddedTestAggregateStoreConfiguration.class})
@EnableAutoConfiguration
public class AccountServiceRestApiIntegrationTestConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
