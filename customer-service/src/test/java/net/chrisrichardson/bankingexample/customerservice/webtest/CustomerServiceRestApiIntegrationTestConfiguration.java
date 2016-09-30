package net.chrisrichardson.bankingexample.customerservice.webtest;

import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import net.chrisrichardson.bankingexample.customerservice.web.CustomerWebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({CustomerWebConfiguration.class, EmbeddedTestAggregateStoreConfiguration.class})
@EnableAutoConfiguration
public class CustomerServiceRestApiIntegrationTestConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
