package net.chrisrichardson.bankingexample.moneytransferservice.webtest;

import io.eventuate.tram.inmemory.TramInMemoryConfiguration;
import net.chrisrichardson.bankingexample.moneytransferservice.web.MoneyTransferWebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@Import({MoneyTransferWebConfiguration.class, TramInMemoryConfiguration.class})
@EnableAutoConfiguration
public class MoneyTransferServiceRestApiIntegrationTestConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder.setType(EmbeddedDatabaseType.H2)
            .addScript("eventuate-tram-embedded-schema.sql")
            .addScript("eventuate-tram-sagas-embedded.sql")
            .build();
  }

}
