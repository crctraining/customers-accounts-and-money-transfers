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
    return events(new AccountOpenedEvent(cmd.getAccountInfo()));
  }

  public void apply(AccountOpenedEvent event) {
    AccountInfo accountInfo = event.getAccountInfo();
    this.customerId = accountInfo.getCustomerId();
    this.name = accountInfo.getName();
    this.balance = accountInfo.getBalance();
  }

  public List<Event> process(DebitAccountCommand cmd) {
    if (balance.isGreaterOrEqualThan(cmd.getAmount()))
      return events(new AccountDebitedEvent(customerId, cmd.getAmount(), balance.subtract(cmd.getAmount()), cmd.getTransactionId()));
    else
      return events(new AccountDebitFailedDueToInsufficientFundsEvent(cmd.getTransactionId()));
  }

  public void apply(AccountDebitedEvent event) {
    this.balance = event.getNewBalance();
  }

  public void apply(AccountDebitFailedDueToInsufficientFundsEvent event) {
    // no op
  }

  public List<Event> process(CreditAccountCommand cmd) {
    return events(new AccountCreditedEvent(customerId, cmd.getAmount(), balance.add(cmd.getAmount()), cmd.getTransactionId()));
  }

  public void apply(AccountCreditedEvent event) {
    this.balance = event.getNewBalance();
  }

}
