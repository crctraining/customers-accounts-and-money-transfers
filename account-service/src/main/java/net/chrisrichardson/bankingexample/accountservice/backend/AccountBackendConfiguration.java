package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableTransactionManagement
@EnableEventHandlers
public class AccountBackendConfiguration {

  @Bean
  public AccountService accountService(CustomerRepository customerRepository, AggregateRepository<Account, AccountCommand> accountAggregateRepository) {
    return new AccountService(customerRepository, accountAggregateRepository);
  }

  @Bean
  @Profile("!eurekaServiceDiscovery")
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public CustomerRepository customerRemoteRepository(RestTemplate restTemplate) {
    return new CustomerRepositoryProxy(restTemplate);
  }

  @Bean
  public AccountEventSubscriber accountEventSubscriber() {
    return new AccountEventSubscriber();
  }

  @Bean
  public AggregateRepository<Account, AccountCommand> accountAggregateRepository(EventuateAggregateStore aggregateStore) {
    return new AggregateRepository<Account, AccountCommand>(Account.class, aggregateStore);
  }
}
