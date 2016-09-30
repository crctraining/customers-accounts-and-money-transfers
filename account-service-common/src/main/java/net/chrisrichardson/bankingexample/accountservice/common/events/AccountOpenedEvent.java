package net.chrisrichardson.bankingexample.accountservice.common.events;

import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;

public class AccountOpenedEvent implements AccountEvent {
  private AccountInfo accountInfo;

  public AccountOpenedEvent() {
  }

  public AccountOpenedEvent(AccountInfo accountInfo) {
    this.accountInfo = accountInfo;
  }

  public AccountInfo getAccountInfo() {
    return accountInfo;
  }
}
