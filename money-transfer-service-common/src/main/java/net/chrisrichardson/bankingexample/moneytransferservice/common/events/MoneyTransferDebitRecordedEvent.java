package net.chrisrichardson.bankingexample.moneytransferservice.common.events;

import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;

public class MoneyTransferDebitRecordedEvent implements MoneyTransferEvent {
  private MoneyTransferInfo moneyTransferInfo;

  private MoneyTransferDebitRecordedEvent() {
  }

  public MoneyTransferDebitRecordedEvent(MoneyTransferInfo moneyTransferInfo) {
    this.moneyTransferInfo = moneyTransferInfo;
  }

  public MoneyTransferInfo getMoneyTransferInfo() {
    return moneyTransferInfo;
  }
}
