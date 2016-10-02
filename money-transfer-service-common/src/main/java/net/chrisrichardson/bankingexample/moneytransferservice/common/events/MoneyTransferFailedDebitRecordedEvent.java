package net.chrisrichardson.bankingexample.moneytransferservice.common.events;

import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;

public class MoneyTransferFailedDebitRecordedEvent implements MoneyTransferEvent {
  private MoneyTransferInfo details;

  private MoneyTransferFailedDebitRecordedEvent() {
  }

  public MoneyTransferFailedDebitRecordedEvent(MoneyTransferInfo details) {
    this.details = details;
  }

  public MoneyTransferInfo getDetails() {
    return details;
  }
}
