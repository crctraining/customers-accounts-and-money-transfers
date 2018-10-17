package net.chrisrichardson.bankingexample.accountservice.backend;

import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitFailedDueToInsufficientFundsEvent;
import net.chrisrichardson.bankingexample.commondomain.Money;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AccountTest {

  private Account aggregate;

  @Test
  public void shouldOpen() {
    AccountInfo accountInfo = AccountMother.makeAccount();

    Money initialBalance = accountInfo.getBalance();

    openAccount(accountInfo);

    assertEquals(initialBalance, aggregate.getBalance());
  }

  private void openAccount(AccountInfo accountInfo) {
    aggregate = new Account(accountInfo);
    aggregate.noteCustomerValidated();
  }

  @Test
  public void shouldDebitAndCredit() {
    String transactionId = "transaction-xyz";

    AccountInfo accountInfo = AccountMother.makeAccount();

    Money initialBalance = accountInfo.getBalance();

    openAccount(accountInfo);

    assertEquals(initialBalance, aggregate.getBalance());

    Money debitAmount = new Money("5.13");

    debit(debitAmount, transactionId);

    assertEquals(initialBalance.subtract(debitAmount), aggregate.getBalance());

    Money creditAmount = new Money("4.32");

    credit(creditAmount, transactionId);

    assertEquals(initialBalance.subtract(debitAmount).add(creditAmount), aggregate.getBalance());
  }

  private void credit(Money amount, String transactionId) {
    aggregate.credit(amount);
  }

  private void debit(Money amount, String transactionId) {
    aggregate.debit(amount);
  }

  @Test
  public void shouldFailDueToInsufficientFunds() {
    String transactionId = "transaction-xyz";

    AccountInfo accountInfo = AccountMother.makeAccount();

    Money initialBalance = accountInfo.getBalance();

    openAccount(accountInfo);

    assertEquals(initialBalance, aggregate.getBalance());

    Money debitAmount = new Money("5.13").add(initialBalance);

    try {
      debit(debitAmount, transactionId);
      fail();
    } catch (InsufficientFundsException e) {
      //expected
    }

    assertEquals(initialBalance, aggregate.getBalance());


  }
}