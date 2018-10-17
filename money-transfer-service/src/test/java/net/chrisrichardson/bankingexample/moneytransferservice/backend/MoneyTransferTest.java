package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MoneyTransferTest  {

  private MoneyTransfer aggregate;

  @Test
  public void shouldTransferMoney() {
    MoneyTransferInfo moneyTransferInfo = MoneyTransferMother.makeMoneyTransfer();
    aggregate = new MoneyTransfer(moneyTransferInfo);

    assertEquals(MoneyTransferState.IN_PROGRESS, aggregate.getState());

    aggregate.complete();

    assertEquals(MoneyTransferState.COMPLETED, aggregate.getState());

  }

  @Test
  public void shouldFailDueToInsufficientFunds() {
    MoneyTransferInfo moneyTransferInfo = MoneyTransferMother.makeMoneyTransfer();
    aggregate = new MoneyTransfer(moneyTransferInfo);

    assertEquals(MoneyTransferState.IN_PROGRESS, aggregate.getState());

    aggregate.cancel();

    assertEquals(MoneyTransferState.FAILED_DUE_TO_INSUFFICIENT_FUNDS, aggregate.getState());


  }
}