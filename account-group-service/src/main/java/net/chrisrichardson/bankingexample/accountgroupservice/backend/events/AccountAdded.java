package net.chrisrichardson.bankingexample.accountgroupservice.backend.events;

public class AccountAdded implements AccountGroupEvent {
  private String accountId;

  public AccountAdded() {
  }

  public AccountAdded(String accountId) {

    this.accountId = accountId;
  }

  public String getAccountId() {
    return accountId;
  }

}
