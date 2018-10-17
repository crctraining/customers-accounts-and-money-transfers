package net.chrisrichardson.bankingexample.customerservice.common;

public class CustomerValidatedEvent implements CustomerEvent {
  private long accountId;

  public CustomerValidatedEvent(long accountId) {
    this.accountId = accountId;
  }

  private CustomerValidatedEvent() {
  }

  public long getAccountId() {
    return accountId;
  }

}
