package net.chrisrichardson.bankingexample.moneytransferservice.sagas;

import net.chrisrichardson.bankingexample.accountservice.common.commands.AccountServiceChannels;
import net.chrisrichardson.bankingexample.accountservice.common.commands.CreditCommand;
import net.chrisrichardson.bankingexample.accountservice.common.commands.DebitCommand;
import net.chrisrichardson.bankingexample.commondomain.Money;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.CancelMoneyTransferCommand;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.CompleteMoneyTransferCommand;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.MoneyTransferServiceChannels;
import org.junit.Test;

import static io.eventuate.tram.sagas.testing.SagaUnitTestSupport.given;
import static org.junit.Assert.*;

public class TransferMoneySagaTest {

  private long moneyTransferId = 104L;
  private long fromAccountId = 106L;
  private long toAccountId = 107L;
  private Money amount = new Money("78.90");
  private MoneyTransferInfo moneyTransferInfo = new MoneyTransferInfo(fromAccountId, toAccountId, amount);
  private AccountServiceProxy accountServiceProxy = new AccountServiceProxy();
  private MoneyTransferServiceProxy moneyTransferServiceProxy = new MoneyTransferServiceProxy();

  private TransferMoneySaga makeTransferMoneySaga() {
    return new TransferMoneySaga(accountServiceProxy, moneyTransferServiceProxy);
  }

  @Test
  public void shouldTransferMoney() {
    given()
      .saga(makeTransferMoneySaga(),
                    new TransferMoneySagaState(moneyTransferId, moneyTransferInfo)).
    expect().
      command(new DebitCommand(fromAccountId, amount)).
      to(AccountServiceChannels.accountServiceChannel).
    andGiven().
      successReply().
    expect().
      command(new CreditCommand(toAccountId, amount)).
      to(AccountServiceChannels.accountServiceChannel).
    andGiven().
      successReply().
    expect().
      command(new CompleteMoneyTransferCommand(moneyTransferId)).
      to(MoneyTransferServiceChannels.moneyTransferServiceChannel)
    ;
  }

  @Test
  public void shouldFailDueToInsufficientFunds() {
    given()
      .saga(makeTransferMoneySaga(),
                    new TransferMoneySagaState(moneyTransferId, moneyTransferInfo)).
    expect().
      command(new DebitCommand(fromAccountId, amount)).
      to(AccountServiceChannels.accountServiceChannel).
    andGiven().
      successReply().
    expect().
      command(new CreditCommand(toAccountId, amount)).
      to(AccountServiceChannels.accountServiceChannel).
    andGiven().
      failureReply().
    expect().
      command(new CreditCommand(fromAccountId, amount)).
      to(AccountServiceChannels.accountServiceChannel).
      andGiven().
      successReply().
    expect().
      command(new CancelMoneyTransferCommand(moneyTransferId)).
      to(MoneyTransferServiceChannels.moneyTransferServiceChannel)
    ;
  }



}