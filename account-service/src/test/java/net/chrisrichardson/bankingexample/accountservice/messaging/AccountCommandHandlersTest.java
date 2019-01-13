package net.chrisrichardson.bankingexample.accountservice.messaging;

import io.eventuate.tram.commands.common.Success;
import net.chrisrichardson.bankingexample.accountservice.backend.AccountService;
import net.chrisrichardson.bankingexample.accountservice.backend.InsufficientFundsException;
import net.chrisrichardson.bankingexample.accountservice.common.commands.CreditCommand;
import net.chrisrichardson.bankingexample.accountservice.common.commands.DebitCommand;
import net.chrisrichardson.bankingexample.accountservice.common.commands.InsufficientFundsReply;
import org.junit.Before;
import org.junit.Test;

import static net.chrisrichardson.bankingexample.accountservice.backend.AccountMother.accountId;
import static net.chrisrichardson.bankingexample.accountservice.backend.AccountMother.amount;
import static net.chrisrichardson.bankingexample.accountservice.messaging.CommandMessageHandlerUnitTestSupport.assertReplyTypeEquals;
import static net.chrisrichardson.bankingexample.accountservice.messaging.CommandMessageHandlerUnitTestSupport.given;
import static org.mockito.Mockito.*;

public class AccountCommandHandlersTest {

  private AccountService accountService;
  private AccountCommandHandlers accountCommandHandlers;

  @Before
  public void setUp() {
    accountService = mock(AccountService.class);
    accountCommandHandlers = new AccountCommandHandlers(accountService);
  }

  @Test
  public void shouldHandleDebitCommand() {
    given().
        commandHandlers(accountCommandHandlers.commandHandlers()).
    when().
        receives(new DebitCommand(accountId, amount)).
    then().
        verify((reply) -> {
          verify(accountService).debit(accountId, amount);
          assertReplyTypeEquals(Success.class, reply);
        })
    ;

  }

  @Test
  public void shouldHandleDebitCommandThatFailsDueToInsufficientFunds() {

    doThrow(new InsufficientFundsException())
      .when(accountService).debit(accountId, amount);

    given().
        commandHandlers(accountCommandHandlers.commandHandlers()).
    when().
        receives(new DebitCommand(accountId, amount)).
    then().
        verify((reply) -> {
          verify(accountService).debit(accountId, amount);
          assertReplyTypeEquals(InsufficientFundsReply.class, reply);
        })
    ;

  }

  @Test
  public void shouldHandleCreditCommand() {
    given().
        commandHandlers(accountCommandHandlers.commandHandlers()).
    when().
        receives(new CreditCommand(accountId, amount)).
    then().
        verify((reply) -> {
          verify(accountService).credit(accountId, amount);
          assertReplyTypeEquals(Success.class, reply);
        })
    ;

  }



}