package net.chrisrichardson.bankingexample.customerviewservice.backend;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountCreditedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountOpenedEvent;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerCreatedEvent;

public class CustomerViewCustomerEventSubscriber {

  private CustomerViewService customerViewService;

  public CustomerViewCustomerEventSubscriber(CustomerViewService customerViewService) {
    this.customerViewService = customerViewService;
  }

    public DomainEventHandlers domainEventHandlers() {
      return DomainEventHandlersBuilder
              .forAggregateType("net.chrisrichardson.bankingexample.customerservice.backend.Customer")
              .onEvent(CustomerCreatedEvent.class, this::handleCustomerCreatedEvent)
              .build();
    }

  public void handleCustomerCreatedEvent(DomainEventEnvelope<CustomerCreatedEvent> dee) {
    customerViewService.createCustomer(dee.getAggregateId(), dee.getEvent().getCustomerInfo());

  }



}
