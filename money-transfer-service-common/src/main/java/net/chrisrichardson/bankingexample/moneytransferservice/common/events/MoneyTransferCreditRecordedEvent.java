package net.chrisrichardson.bankingexample.moneytransferservice.common.events;

import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;

public class MoneyTransferCreditRecordedEvent implements MoneyTransferEvent {
  private MoneyTransferInfo details;

  private MoneyTransferCreditRecordedEvent() {
  }

  public MoneyTransferCreditRecordedEvent(MoneyTransferInfo details) {
    this.details = details;
  }

  public MoneyTransferInfo getDetails() {
    return details;
  }
}
