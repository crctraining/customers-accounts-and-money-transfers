package net.chrisrichardson.bankingexample.accountgroupservice.backend.events;

public class MissingParentCreated implements  AccountGroupEvent {
  private String childId;

  public MissingParentCreated() {
  }

  public MissingParentCreated(String childId) {
    this.childId = childId;
  }

  public String getChildId() {
    return childId;
  }
}
