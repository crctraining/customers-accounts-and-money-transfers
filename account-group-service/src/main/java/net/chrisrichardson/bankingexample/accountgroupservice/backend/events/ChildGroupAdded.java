package net.chrisrichardson.bankingexample.accountgroupservice.backend.events;

public class ChildGroupAdded implements AccountGroupEvent {
  private String childId;

  public ChildGroupAdded() {
  }

  public ChildGroupAdded(String childId) {
    this.childId = childId;
  }

  public String getChildId() {
    return childId;
  }
}
