package net.chrisrichardson.bankingexample.accountservice.messaging;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import net.chrisrichardson.bankingexample.accountservice.backend.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountMessagingConfiguration {

  @Bean
  public AccountEventHandlers accountEventHandlers(AccountService accountService) {
    return new AccountEventHandlers(accountService);
  }

  @Bean
  public DomainEventDispatcher accountServiceDomainEventDispatcher(AccountEventHandlers orderHistoryEventHandlers, MessageConsumer messageConsumer) {
    return new DomainEventDispatcher("accountServiceDomainEventDispatcher", orderHistoryEventHandlers.domainEventHandlers(), messageConsumer);
  }

  @Bean
  public AccountCommandHandlers accountServiceCommandHandlers(AccountService accountService) {
    return new AccountCommandHandlers(accountService);
  }

  @Bean
  public SagaCommandDispatcher orderCommandHandlersDispatcher(AccountCommandHandlers accountServiceCommandHandlers) {
    return new SagaCommandDispatcher("accountServiceCommands", accountServiceCommandHandlers.commandHandlers());
  }


}
