package net.chrisrichardson.bankingexample.moneytransferservice.webtest;

import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import net.chrisrichardson.bankingexample.moneytransferservice.web.MoneyTransferWebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({MoneyTransferWebConfiguration.class, EmbeddedTestAggregateStoreConfiguration.class})
@EnableAutoConfiguration
public class MoneyTransferServiceRestApiIntegrationTestConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
