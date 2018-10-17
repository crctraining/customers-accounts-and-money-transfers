package net.chrisrichardson.bankingexample.customerviewservice.common;

import net.chrisrichardson.bankingexample.commondomain.Money;

import java.util.Map;

public class AccountView {

  private Money balance;

  private Map<String, AccountChange> changes;

  public Map<String, AccountChange> getChanges() {
    return changes;
  }

  public void setChanges(Map<String, AccountChange> changes) {
    this.changes = changes;
  }

  public Money getBalance() {
    return balance;
  }

  public void setBalance(Money balance) {
    this.balance = balance;
  }
}
