package net.chrisrichardson.bankingexample.accountservice.common.events;

import net.chrisrichardson.bankingexample.commondomain.Money;

public class AccountDebitedEvent extends AccountBalanceChangedEvent {

  public AccountDebitedEvent() {
  }

  public AccountDebitedEvent(String customerId, Money amount, Money newBalance, String transactionId) {
    super(customerId, amount, newBalance, transactionId);
  }

}
