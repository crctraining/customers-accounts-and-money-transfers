package net.chrisrichardson.bankingexample.accountservice.backend;

public class AccountNotOpenException extends RuntimeException {
  public AccountNotOpenException(String message) {
    super(message);
  }
}
