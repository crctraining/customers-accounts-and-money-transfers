package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan
@Import(TramEventsPublisherConfiguration.class)
public class CustomerBackendConfiguration {

  @Bean
  public CustomerService customerService(CustomerRepository customerRepository, DomainEventPublisher domainEventPublisher) {
    return new CustomerService(customerRepository, domainEventPublisher);
  }

  @Bean
  public CustomerEventHandlers customerEventHandlers(CustomerService customerService) {
    return new CustomerEventHandlers(customerService);
  }

  @Bean
  public DomainEventDispatcher customerServiceDomainEventDispatcher(CustomerEventHandlers orderHistoryEventHandlers, MessageConsumer messageConsumer) {
    return new DomainEventDispatcher("customerServiceDomainEventDispatcher", orderHistoryEventHandlers.domainEventHandlers(), messageConsumer);
  }

}
