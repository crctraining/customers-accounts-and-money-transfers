package net.chrisrichardson.bankingexample.accountservice.backend;

import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.common.AccountState;
import net.chrisrichardson.bankingexample.commondomain.Money;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Access(AccessType.FIELD)
public class Account  {

  @Id
  @GeneratedValue
  private Long id;

  private AccountState state;

  private long customerId;


  private String name;

  @Embedded
  private Money balance;

  private Account() {
  }

  public Account(AccountInfo accountInfo) {
    this.customerId = accountInfo.getCustomerId();
    this.name = accountInfo.getName();
    this.balance = accountInfo.getBalance();
    this.state = AccountState.PENDING_CUSTOMER_VALIDATION;
  }

  public Long getId() {
    return id;
  }

  void setId(Long id) {
    this.id = id;
  }

  public long getCustomerId() {
    return customerId;
  }

  public String getName() {
    return name;
  }

  public Money getBalance() {
    return balance;
  }

  public AccountInfo getAccountInfo() {
    return new AccountInfo(customerId, name, balance);
  }

  public AccountState getState() {
    return state;
  }

  public void debit(Money amount) {
    verifyOpen();
    if (balance.isGreaterOrEqualThan(amount))
      balance = balance.subtract(amount);
    else
      throw new InsufficientFundsException();
  }

  public void credit(Money amount) {
    verifyOpen();
    this.balance = balance.add(amount);
  }

  private void verifyOpen() {
    if (state != AccountState.OPEN)
      throw new AccountNotOpenException("Account is this state: " + state);
  }


  public void noteCustomerValidated() {
    state = AccountState.OPEN;
  }

  public void noteCustomerValidationFailed() {
    state = AccountState.CUSTOMER_VALIDATION_FAILED;
  }

}
