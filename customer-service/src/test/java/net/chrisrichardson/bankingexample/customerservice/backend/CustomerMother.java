package net.chrisrichardson.bankingexample.customerservice.backend;

import net.chrisrichardson.bankingexample.customerservice.common.Address;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import net.chrisrichardson.bankingexample.customerservice.common.Name;

public class CustomerMother {

  public static CustomerInfo makeCustomer() {
    return new CustomerInfo(
            new Name("John", "Doe"), "510-555-1212",
            new Address("1 high street", null, "Oakland", "CA", "94719"),
            "xxx-yy-zzz");
  }
}
