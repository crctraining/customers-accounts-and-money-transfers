package net.chrisrichardson.bankingexample.moneytransferservice.common.events;

import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;

public class MoneyTransferCreatedEvent implements MoneyTransferEvent {
  private MoneyTransferInfo moneyTransferInfo;

  public MoneyTransferCreatedEvent() {
  }

  public MoneyTransferCreatedEvent(MoneyTransferInfo moneyTransferInfo) {
    this.moneyTransferInfo = moneyTransferInfo;
  }

  public MoneyTransferInfo getMoneyTransferInfo() {
    return moneyTransferInfo;
  }
}
