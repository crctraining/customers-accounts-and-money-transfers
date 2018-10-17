package net.chrisrichardson.bankingexample.accountgroupservice.backend.commands;

public class CreateMissingParent implements AccountGroupCommand {
  private String childId;

  public CreateMissingParent(String childId) {
    this.childId = childId;
  }

  public String getChildId() {
    return childId;
  }
}
