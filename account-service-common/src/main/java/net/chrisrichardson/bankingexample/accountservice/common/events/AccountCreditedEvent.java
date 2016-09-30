package net.chrisrichardson.bankingexample.accountservice.common.events;

import net.chrisrichardson.bankingexample.commondomain.Money;

public class AccountCreditedEvent extends AccountBalanceChangedEvent {

  public AccountCreditedEvent() {
  }

  public AccountCreditedEvent(String customerId, Money amount, Money newBalance, String transactionId) {
    super(customerId, amount, newBalance, transactionId);
  }

}
