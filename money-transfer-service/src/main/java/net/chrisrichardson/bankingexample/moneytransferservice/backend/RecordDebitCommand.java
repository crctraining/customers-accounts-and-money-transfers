package net.chrisrichardson.bankingexample.moneytransferservice.backend;

public class RecordDebitCommand implements MoneyTransferCommand {
  private String accountId;


  public RecordDebitCommand(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountId() {
    return accountId;
  }

}
