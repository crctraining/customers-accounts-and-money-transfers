package net.chrisrichardson.bankingexample.accountgroupservice.backend.events;

public class AccountGroupCreated implements AccountGroupEvent {
  private String name;

  private AccountGroupCreated() {
  }

  public AccountGroupCreated(String parentId, String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
