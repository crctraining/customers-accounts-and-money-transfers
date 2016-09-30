package net.chrisrichardson.bankingexample.accountservice.backend;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String message) {
    super(message);
  }
}
