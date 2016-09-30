package net.chrisrichardson.bankingexample.accountservice.common.events;

public class AccountDebitFailedDueToInsufficientFundsEvent implements AccountEvent {
  private String transactionId;

  public AccountDebitFailedDueToInsufficientFundsEvent() {
  }

  public AccountDebitFailedDueToInsufficientFundsEvent(String transactionId) {

    this.transactionId = transactionId;
  }

  public String getTransactionId() {
    return transactionId;
  }
}
