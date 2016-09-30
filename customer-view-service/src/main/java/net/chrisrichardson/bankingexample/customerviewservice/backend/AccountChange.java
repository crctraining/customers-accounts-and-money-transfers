package net.chrisrichardson.bankingexample.customerviewservice.backend;

import net.chrisrichardson.bankingexample.commondomain.Money;

public class AccountChange {
  private AccountChangeType type;
  private Money amount;
  private Money balance;
  private String transactionId;

  public AccountChange() {
  }

  public AccountChange(AccountChangeType type, Money balance) {
    this.type = type;
    this.balance = balance;
  }

  public AccountChange(AccountChangeType type, Money amount, Money newBalance, String transactionId) {

    this.type = type;
    this.amount = amount;
    balance = newBalance;
    this.transactionId = transactionId;
  }

  public void setType(AccountChangeType type) {
    this.type = type;
  }

  public void setBalance(Money balance) {
    this.balance = balance;
  }

  public AccountChangeType getType() {
    return type;
  }

  public Money getBalance() {
    return balance;
  }

  public Money getAmount() {
    return amount;
  }

  public String getTransactionId() {
    return transactionId;
  }
}
