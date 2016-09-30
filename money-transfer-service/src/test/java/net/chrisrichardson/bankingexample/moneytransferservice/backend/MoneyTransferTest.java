package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.testutil.AggregateTest;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MoneyTransferTest extends AggregateTest<MoneyTransfer, MoneyTransferCommand> {

  public MoneyTransferTest() {
    super(MoneyTransfer.class);
  }

  @Test
  public void shouldTransferMoney() {
    MoneyTransferInfo moneyTransferInfo = MoneyTransferMother.makeMoneyTransfer();
    create(new CreateMoneyTransferCommand(moneyTransferInfo));

    assertEquals(MoneyTransferState.NEW, aggregate.getState());

    update(new RecordDebitCommand(moneyTransferInfo.getFromAccountId()));

    assertEquals(MoneyTransferState.DEBITED, aggregate.getState());

    update(new RecordCreditCommand(moneyTransferInfo.getToAccountId()));

    assertEquals(MoneyTransferState.COMPLETED, aggregate.getState());

  }

  @Test
  public void shouldFailDueToInsufficientFunds() {
    MoneyTransferInfo moneyTransferInfo = MoneyTransferMother.makeMoneyTransfer();
    create(new CreateMoneyTransferCommand(moneyTransferInfo));

    assertEquals(MoneyTransferState.NEW, aggregate.getState());

    update(new RecordDebitFailedDueToInsufficientFundsCommand(moneyTransferInfo.getFromAccountId()));

    assertEquals(MoneyTransferState.FAILED_DUE_TO_INSUFFICIENT_FUNDS, aggregate.getState());


  }
}