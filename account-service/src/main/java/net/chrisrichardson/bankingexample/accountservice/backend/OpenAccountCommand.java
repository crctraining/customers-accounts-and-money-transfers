package net.chrisrichardson.bankingexample.accountservice.backend;


import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;

public class OpenAccountCommand implements AccountCommand {

  private AccountInfo accountInfo;

  public OpenAccountCommand(AccountInfo accountInfo) {
    this.accountInfo = accountInfo;
  }

  public AccountInfo getAccountInfo() {
    return accountInfo;
  }
}
