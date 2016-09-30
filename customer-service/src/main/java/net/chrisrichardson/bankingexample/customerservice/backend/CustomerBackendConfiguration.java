package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class CustomerBackendConfiguration {

  @Bean
  public CustomerService customerService(AggregateRepository<Customer, CustomerCommand> customerRepository) {
    return new CustomerService(customerRepository);
  }

  @Bean
  public AggregateRepository<Customer, CustomerCommand> customerRepository(EventuateAggregateStore aggregateStore) {
    return new AggregateRepository<>(Customer.class, aggregateStore);
  }
}
