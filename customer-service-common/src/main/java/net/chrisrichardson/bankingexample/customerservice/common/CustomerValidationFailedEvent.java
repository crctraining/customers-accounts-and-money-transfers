package net.chrisrichardson.bankingexample.customerservice.common;

public class CustomerValidationFailedEvent implements CustomerEvent {
  private long accountId;

  public CustomerValidationFailedEvent(long accountId) {
    this.accountId = accountId;
  }

  private CustomerValidationFailedEvent() {
  }

  public long getAccountId() {
    return accountId;
  }

}
