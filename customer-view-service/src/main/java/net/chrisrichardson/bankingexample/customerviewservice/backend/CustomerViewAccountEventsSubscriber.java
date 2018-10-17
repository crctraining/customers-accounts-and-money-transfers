package net.chrisrichardson.bankingexample.customerviewservice.backend;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountCreditedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountOpenedEvent;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerCreatedEvent;

public class CustomerViewAccountEventsSubscriber {

  private CustomerViewService customerViewService;

  public CustomerViewAccountEventsSubscriber(CustomerViewService customerViewService) {
    this.customerViewService = customerViewService;
  }

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType("net.chrisrichardson.bankingexample.accountservice.backend.Account")
            .onEvent(AccountOpenedEvent.class, this::handleAccountOpenedEvent)
            .onEvent(AccountDebitedEvent.class, this::handleAccountDebitedEvent)
            .onEvent(AccountCreditedEvent.class, this::handleAccountCreditedEvent)
            .build();
  }

  public void handleAccountOpenedEvent(DomainEventEnvelope<AccountOpenedEvent> dee) {
    customerViewService.openAccount(dee.getEventId(), dee.getAggregateId(), dee.getEvent().getAccountInfo());
  }

  public void handleAccountDebitedEvent(DomainEventEnvelope<AccountDebitedEvent> de) {
    AccountDebitedEvent event = de.getEvent();
    customerViewService.debitAccount(de.getEventId(), de.getAggregateId(), Long.toString(event.getCustomerId()),
            event.getAmount(),
            event.getNewBalance(), event.getTransactionId());
  }

  public void handleAccountCreditedEvent(DomainEventEnvelope<AccountCreditedEvent> de) {
    AccountCreditedEvent event = de.getEvent();
    customerViewService.creditAccount(de.getEventId(), de.getAggregateId(), Long.toString(event.getCustomerId()),
            event.getAmount(),
            event.getNewBalance(), event.getTransactionId());
  }


}
