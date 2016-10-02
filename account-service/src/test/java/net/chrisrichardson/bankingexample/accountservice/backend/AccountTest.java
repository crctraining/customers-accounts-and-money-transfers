package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.testutil.AggregateTest;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitFailedDueToInsufficientFundsEvent;
import net.chrisrichardson.bankingexample.commondomain.Money;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest extends AggregateTest<Account, AccountCommand> {

  public AccountTest() {
    super(Account.class);
  }

  @Test
  public void shouldOpen() {
    AccountInfo accountInfo = AccountMother.makeAccount("a-customer");

    Money initialBalance = accountInfo.getBalance();

    create(new OpenAccountCommand(accountInfo));

    assertEquals(initialBalance, aggregate.getBalance());
  }

  @Test
  public void shouldDebitAndCredit() {
    String transactionId = "transaction-xyz";

    AccountInfo accountInfo = AccountMother.makeAccount("a-customer");

    Money initialBalance = accountInfo.getBalance();

    create(new OpenAccountCommand(accountInfo));

    assertEquals(initialBalance, aggregate.getBalance());

    Money debitAmount = new Money("5.13");

    update(new DebitAccountCommand(debitAmount, transactionId));

    assertEquals(initialBalance.subtract(debitAmount), aggregate.getBalance());

    Money creditAmount = new Money("4.32");

    update(new CreditAccountCommand(creditAmount, transactionId));

    assertEquals(initialBalance.subtract(debitAmount).add(creditAmount), aggregate.getBalance());
  }

  @Test
  public void shouldFailDueToInsufficientFunds() {
    String transactionId = "transaction-xyz";

    AccountInfo accountInfo = AccountMother.makeAccount("a-customer");

    Money initialBalance = accountInfo.getBalance();

    create(new OpenAccountCommand(accountInfo));

    assertEquals(initialBalance, aggregate.getBalance());

    Money debitAmount = new Money("5.13").add(initialBalance);

    update(new DebitAccountCommand(debitAmount, transactionId));

    assertEquals(initialBalance, aggregate.getBalance());

    assertEventsEquals(new AccountDebitFailedDueToInsufficientFundsEvent(transactionId));

  }
}