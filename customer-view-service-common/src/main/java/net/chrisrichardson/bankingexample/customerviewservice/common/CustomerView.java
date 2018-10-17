package net.chrisrichardson.bankingexample.customerviewservice.common;

import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;

import java.util.Map;

public class CustomerView {
  private String id;
  private CustomerInfo customerInfo;
  private Map<String, AccountView> accounts;

  public CustomerView() {
  }

  public CustomerView(String id) {
    this.id = id;
  }

  public CustomerView(String id, CustomerInfo customerInfo) {

    this.id = id;
    this.customerInfo = customerInfo;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CustomerInfo getCustomerInfo() {
    return customerInfo;
  }

  public void setCustomerInfo(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
  }

  public Map<String, AccountView> getAccounts() {
    return accounts;
  }

  public void setAccounts(Map<String, AccountView> accounts) {
    this.accounts = accounts;
  }
}
