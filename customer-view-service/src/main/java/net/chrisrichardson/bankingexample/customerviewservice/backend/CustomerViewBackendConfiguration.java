package net.chrisrichardson.bankingexample.customerviewservice.backend;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
@EnableEventHandlers
public class CustomerViewBackendConfiguration {

  @Bean
  public CustomerViewService customerViewService(MongoTemplate mongoTemplate, CustomerViewRepository customerViewRepository) {
    return new CustomerViewService(mongoTemplate, customerViewRepository);
  }

  @Bean
  public CustomerViewEventSubscriber customerViewEventSubscriber(CustomerViewService customerViewService) {
    return new CustomerViewEventSubscriber(customerViewService);
  }
}
