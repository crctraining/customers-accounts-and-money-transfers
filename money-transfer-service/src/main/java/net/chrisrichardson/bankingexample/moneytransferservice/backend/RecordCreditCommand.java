package net.chrisrichardson.bankingexample.moneytransferservice.backend;

public class RecordCreditCommand implements MoneyTransferCommand {
  private String accountId;

  public RecordCreditCommand(String accountId) {

    this.accountId = accountId;
  }

  public String getAccountId() {
    return accountId;
  }
}
