package net.chrisrichardson.bankingexample.customerviewservice.backend;

import java.util.Map;

public class AccountView {

  private Map<String, AccountChange> changes;

  public Map<String, AccountChange> getChanges() {
    return changes;
  }

  public void setChanges(Map<String, AccountChange> changes) {
    this.changes = changes;
  }
}
