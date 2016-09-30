package net.chrisrichardson.bankingexample.customerviewservice.backend;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountBalanceChangedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountCreditedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountOpenedEvent;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerCreatedEvent;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferCreatedEvent;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferCreditRecordedEvent;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferDebitRecordedEvent;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferFailedDebitRecordedEvent;

@EventSubscriber(id="bankingExampleCustomerViewEventSubscriber")
public class CustomerViewEventSubscriber {

  private CustomerViewService customerViewService;

  public CustomerViewEventSubscriber(CustomerViewService customerViewService) {
    this.customerViewService = customerViewService;
  }

  @EventHandlerMethod
  public void createCustomer(DispatchedEvent<CustomerCreatedEvent> de) {
    customerViewService.createCustomer(de.getEntityId(), de.getEvent().getCustomerInfo());

  }

  @EventHandlerMethod
  public void openAccount(DispatchedEvent<AccountOpenedEvent> de) {
    customerViewService.openAccount(de.getEventId(), de.getEntityId(), de.getEvent().getAccountInfo());
  }

  @EventHandlerMethod
  public void debitAccount(DispatchedEvent<AccountDebitedEvent> de) {
    AccountBalanceChangedEvent event = de.getEvent();
    customerViewService.debitAccount(de.getEventId(), de.getEntityId(), event.getCustomerId(),
            event.getAmount(),
            event.getNewBalance(), event.getTransactionId());
  }

  @EventHandlerMethod
  public void creditAccount(DispatchedEvent<AccountCreditedEvent> de) {
    AccountBalanceChangedEvent event = de.getEvent();
    customerViewService.creditAccount(de.getEventId(), de.getEntityId(), event.getCustomerId(),
            event.getAmount(),
            event.getNewBalance(), event.getTransactionId());
  }

  @EventHandlerMethod
  public void createMoneyTransfer(DispatchedEvent<MoneyTransferCreatedEvent> de) {
    // TODO
  }

  @EventHandlerMethod
  public void moneyTransferDebitRecordedEvent(DispatchedEvent<MoneyTransferDebitRecordedEvent> de) {
    // TODO
  }

  @EventHandlerMethod
  public void moneyTransferFailedDebitRecordedEvent(DispatchedEvent<MoneyTransferFailedDebitRecordedEvent> de) {
    // TODO
  }

  @EventHandlerMethod
  public void moneyTransferCreditRecordedEvent(DispatchedEvent<MoneyTransferCreditRecordedEvent> de) {
    // TODO
  }
}
