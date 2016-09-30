package net.chrisrichardson.bankingexample.moneytransferservice.backend;

public class RecordDebitFailedDueToInsufficientFundsCommand implements MoneyTransferCommand {
  private String accountId;

  public RecordDebitFailedDueToInsufficientFundsCommand(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountId() {
    return accountId;
  }
}
