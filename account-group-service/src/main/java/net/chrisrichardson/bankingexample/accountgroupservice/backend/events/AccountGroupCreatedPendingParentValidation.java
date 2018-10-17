package net.chrisrichardson.bankingexample.accountgroupservice.backend.events;

public class AccountGroupCreatedPendingParentValidation implements AccountGroupEvent {
  private String parentId;
  private String name;

  private AccountGroupCreatedPendingParentValidation() {
  }

  public AccountGroupCreatedPendingParentValidation(String parentId, String name) {
    this.parentId = parentId;
    this.name = name;
  }

  public String getParentId() {
    return parentId;
  }

  public String getName() {
    return name;
  }
}
