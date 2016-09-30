package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableEventHandlers
public class MoneyTransferBackendConfiguration {

  @Bean
  public MoneyTransferService moneyTransferService(AggregateRepository<MoneyTransfer, MoneyTransferCommand> moneyTransferRepository) {
    return new MoneyTransferService(moneyTransferRepository);
  }

  @Bean
  public AggregateRepository<MoneyTransfer, MoneyTransferCommand> moneyTransferRepository(EventuateAggregateStore aggregateStore) {
    return new AggregateRepository<>(MoneyTransfer.class, aggregateStore);
  }

  @Bean
  public MoneyTransferEventSubscriber moneyTransferEventSubscriber() {
    return new MoneyTransferEventSubscriber();
  }
}
