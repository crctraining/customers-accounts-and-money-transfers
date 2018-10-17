package net.chrisrichardson.bankingexample.accountgroupservice.backend.commands;

public class AddAccount implements AccountGroupCommand {
  private String accountId;

  public AddAccount(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountId() {
    return accountId;
  }
}
