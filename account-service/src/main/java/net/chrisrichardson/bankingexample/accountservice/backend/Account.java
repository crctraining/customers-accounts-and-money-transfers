package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountCreditedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitFailedDueToInsufficientFundsEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountOpenedEvent;
import net.chrisrichardson.bankingexample.commondomain.Money;

import java.util.List;

import static io.eventuate.EventUtil.events;

public class Account extends ReflectiveMutableCommandProcessingAggregate<Account, AccountCommand> {

  private String customerId;

  private String name;

  private Money balance;

  public Account() {
  }

  public Account(AccountInfo accountInfo) {
    this.customerId = accountInfo.getCustomerId();
    this.name = accountInfo.getName();
    this.balance = accountInfo.getBalance();
  }

  public String getCustomerId() {
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

  public List<Event> process(OpenAccountCommand cmd) {
    throw new RuntimeException("not yet implemented");
  }

  public void apply(AccountOpenedEvent event) {
    throw new RuntimeException("not yet implemented");
  }

  public List<Event> process(DebitAccountCommand cmd) {
    throw new RuntimeException("not yet implemented");
  }

  public void apply(AccountDebitedEvent event) {
    throw new RuntimeException("not yet implemented");
  }

  public void apply(AccountDebitFailedDueToInsufficientFundsEvent event) {
    throw new RuntimeException("not yet implemented");
  }

  public List<Event> process(CreditAccountCommand cmd) {
    throw new RuntimeException("not yet implemented");
  }

  public void apply(AccountCreditedEvent event) {
    throw new RuntimeException("not yet implemented");
  }

}
