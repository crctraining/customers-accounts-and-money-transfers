package net.chrisrichardson.bankingexample.accountgroupservice.eventhandling;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.AccountGroupBackendConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableEventHandlers
@Import(AccountGroupBackendConfiguration.class)
public class AccountGroupEventHandlingConfiguration {

  @Bean
  public AccountGroupEventHandlers accountGroupEventHandlers() {
    return new AccountGroupEventHandlers();
  }

}
