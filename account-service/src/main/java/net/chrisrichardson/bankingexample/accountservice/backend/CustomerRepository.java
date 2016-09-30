package net.chrisrichardson.bankingexample.accountservice.backend;

public interface CustomerRepository {
  CustomerInfo findCustomerById(String id);
}
