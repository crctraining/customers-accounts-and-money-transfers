package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountOpenedEvent;

public class CustomerEventHandlers {
  private CustomerService customerService;

  public CustomerEventHandlers(CustomerService customerService) {
    this.customerService = customerService;
  }

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType("net.chrisrichardson.bankingexample.accountservice.backend.Account")
            .onEvent(AccountOpenedEvent.class, this::handleAccountOpenedEvent)
            .build();
  }

  private  void handleAccountOpenedEvent(DomainEventEnvelope<AccountOpenedEvent> dee) {
    customerService.validateCustomer(Long.parseLong(dee.getAggregateId()), dee.getEvent().getAccountInfo().getCustomerId());
  }
}
