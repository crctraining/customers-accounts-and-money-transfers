package net.chrisrichardson.bankingexample.customerviewservice.backend;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class CustomerViewBackendConfiguration {

  @Bean
  public CustomerViewService customerViewService(MongoTemplate mongoTemplate, CustomerViewRepository customerViewRepository) {
    return new CustomerViewService(mongoTemplate, customerViewRepository);
  }

  @Bean
  public CustomerViewCustomerEventSubscriber customerViewEventSubscriber(CustomerViewService customerViewService) {
    return new CustomerViewCustomerEventSubscriber(customerViewService);
  }


  @Bean
  public DomainEventDispatcher customerViewServiceCustomerEventsDispatcher(CustomerViewCustomerEventSubscriber customerViewCustomerEventSubscriber, MessageConsumer messageConsumer) {
    return new DomainEventDispatcher("customerViewServiceCustomerEventsDispatcher", customerViewCustomerEventSubscriber.domainEventHandlers(), messageConsumer);
  }

  @Bean
  public CustomerViewAccountEventsSubscriber customerViewAccountEventsSubscriber(CustomerViewService customerViewService) {
    return new CustomerViewAccountEventsSubscriber(customerViewService);
  }


  @Bean
  public DomainEventDispatcher customerViewAccountEventsSubscriberDispatcher(CustomerViewAccountEventsSubscriber customerViewAccountEventsSubscriber, MessageConsumer messageConsumer) {
    return new DomainEventDispatcher("customerViewAccountEventsSubscriberDispatcher", customerViewAccountEventsSubscriber.domainEventHandlers(), messageConsumer);
  }

}
