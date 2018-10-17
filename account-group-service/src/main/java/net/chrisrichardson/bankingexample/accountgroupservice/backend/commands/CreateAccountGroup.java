package net.chrisrichardson.bankingexample.accountgroupservice.backend.commands;

public class CreateAccountGroup implements AccountGroupCommand {
  private String parentId;
  private String name;

  public CreateAccountGroup(String parentId, String name) {
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
