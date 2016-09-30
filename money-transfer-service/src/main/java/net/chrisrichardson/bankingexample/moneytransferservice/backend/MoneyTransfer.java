package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferCreatedEvent;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferCreditRecordedEvent;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferDebitRecordedEvent;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferFailedDebitRecordedEvent;

import java.util.List;

import static io.eventuate.EventUtil.events;

public class MoneyTransfer extends ReflectiveMutableCommandProcessingAggregate<MoneyTransfer, MoneyTransferCommand> {

  private MoneyTransferState state;

  private MoneyTransferInfo moneyTransferInfo;

  public MoneyTransfer() {
  }


  public MoneyTransferInfo getMoneyTransferInfo() {
    return moneyTransferInfo;
  }

  public List<Event> process(CreateMoneyTransferCommand cmd) {
    return events(new MoneyTransferCreatedEvent(cmd.getMoneyTransferInfo()));
  }

  public void apply(MoneyTransferCreatedEvent event) {
    this.state = MoneyTransferState.NEW;
    this.moneyTransferInfo = event.getMoneyTransferInfo();
  }

  public List<Event> process(RecordDebitCommand cmd) {
    switch (state) {
      case NEW:
        return events(new MoneyTransferDebitRecordedEvent(moneyTransferInfo));
      default:
        throw new RuntimeException("Dont know what to do in this state: " + state + ", " + cmd);
    }
  }

  public void apply(MoneyTransferDebitRecordedEvent event) {
    this.state = MoneyTransferState.DEBITED;
  }

  public List<Event> process(RecordCreditCommand cmd) {
    switch (state) {
      case DEBITED:
        return events(new MoneyTransferCreditRecordedEvent(moneyTransferInfo));
      default:
        throw new RuntimeException("Dont know what to do in this state: " + state + ", " + cmd);
    }
  }

  public void apply(MoneyTransferCreditRecordedEvent event) {
    this.state = MoneyTransferState.COMPLETED;
  }

  public List<Event> process(RecordDebitFailedDueToInsufficientFundsCommand cmd) {
    switch (state) {
      case NEW:
        return events(new MoneyTransferFailedDebitRecordedEvent(moneyTransferInfo));
      default:
        throw new RuntimeException("Dont know what to do in this state: " + state + "," + moneyTransferInfo + ", " + cmd);
    }
  }

  public void apply(MoneyTransferFailedDebitRecordedEvent event) {
    this.state = MoneyTransferState.FAILED_DUE_TO_INSUFFICIENT_FUNDS;
  }

  public MoneyTransferState getState() {
    return state;
  }
}
