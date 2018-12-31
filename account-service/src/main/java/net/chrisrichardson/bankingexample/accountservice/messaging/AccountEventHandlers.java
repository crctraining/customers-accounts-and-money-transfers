package net.chrisrichardson.bankingexample.accountservice.messaging;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import net.chrisrichardson.bankingexample.accountservice.backend.AccountService;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerValidatedEvent;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerValidationFailedEvent;

public class AccountEventHandlers {
  private AccountService accountService;


  public AccountEventHandlers(AccountService accountService) {
    this.accountService = accountService;
  }

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType("net.chrisrichardson.bankingexample.customerservice.backend.Customer")
            .onEvent(CustomerValidatedEvent.class, this::handleCustomerValidatedEvent)
            .onEvent(CustomerValidationFailedEvent.class, this::handleCustomerValidationFailedEvent)
            .build();
  }

  private void handleCustomerValidatedEvent(DomainEventEnvelope<CustomerValidatedEvent> dee) {
    throw new RuntimeException("not yet implemented");
  }
  private void handleCustomerValidationFailedEvent(DomainEventEnvelope<CustomerValidationFailedEvent> dee) {
    accountService.noteCustomerValidationFailed(dee.getEvent().getAccountId());
  }
}
