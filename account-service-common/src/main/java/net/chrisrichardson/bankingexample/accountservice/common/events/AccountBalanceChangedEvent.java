package net.chrisrichardson.bankingexample.accountservice.common.events;

import net.chrisrichardson.bankingexample.commondomain.Money;


public class AccountBalanceChangedEvent implements AccountEvent {
  protected Money amount;
  protected Money newBalance;
  protected String transactionId;
  protected String customerId;

  public AccountBalanceChangedEvent(String customerId, Money amount, Money newBalance, String transactionId) {
    this.customerId = customerId;
    this.amount = amount;
    this.newBalance = newBalance;
    this.transactionId = transactionId;
  }

  public AccountBalanceChangedEvent() {
  }

  public Money getAmount() {
    return amount;
  }

  public Money getNewBalance() {
    return newBalance;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public String getCustomerId() {
    return customerId;
  }
}
