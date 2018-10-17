package net.chrisrichardson.bankingexample.apigateway.apicomposition;

import net.chrisrichardson.bankingexample.accountservice.common.GetAccountResponse;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;

public class AccountWithCustomer {
  private GetAccountResponse account;
  private CustomerInfo customerInfo;

  public AccountWithCustomer() {
  }

  public AccountWithCustomer(GetAccountResponse account, CustomerInfo customerInfo) {
    this.account = account;
    this.customerInfo = customerInfo;
  }

  public GetAccountResponse getAccount() {
    return account;
  }

  public void setAccount(GetAccountResponse account) {
    this.account = account;
  }

  public CustomerInfo getCustomerInfo() {
    return customerInfo;
  }

  public void setCustomerInfo(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
  }
}
