package net.chrisrichardson.bankingexample.customerservice.common;

public class CustomerCreatedEvent implements CustomerEvent {
  private CustomerInfo customerInfo;

  public CustomerCreatedEvent() {
  }

  public CustomerCreatedEvent(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
  }

  public CustomerInfo getCustomerInfo() {
    return customerInfo;
  }
}
