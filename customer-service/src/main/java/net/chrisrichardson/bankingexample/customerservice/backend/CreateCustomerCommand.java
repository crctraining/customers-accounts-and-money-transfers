package net.chrisrichardson.bankingexample.customerservice.backend;

import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;

public class CreateCustomerCommand implements CustomerCommand {
  private CustomerInfo customerInfo;

  public CreateCustomerCommand() {
  }

  public CreateCustomerCommand(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
  }


  public CustomerInfo getCustomerInfo() {
    return customerInfo;
  }
}
