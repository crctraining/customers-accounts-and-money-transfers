package net.chrisrichardson.bankingexample.accountservice.backend;


import net.chrisrichardson.bankingexample.commondomain.Money;

public class CreditAccountCommand implements AccountCommand {
  private final Money amount;
  private final String transactionId;

  public CreditAccountCommand(Money amount, String transactionId) {

    this.amount = amount;
    this.transactionId = transactionId;
  }

  public Money getAmount() {
    return amount;
  }

  public String getTransactionId() {
    return transactionId;
  }
}
