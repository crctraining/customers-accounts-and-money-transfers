package net.chrisrichardson.bankingexample.accountgroupservice.backend.commands;

public class AddChildGroup implements AccountGroupCommand {
  private String childId;

  public AddChildGroup(String childId) {
    this.childId = childId;
  }

  public String getChildId() {
    return childId;
  }
}
