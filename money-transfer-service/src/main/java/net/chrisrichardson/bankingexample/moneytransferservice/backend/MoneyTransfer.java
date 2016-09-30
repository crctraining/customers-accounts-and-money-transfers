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
    throw new RuntimeException("not yet implemented");
  }

  public List<Event> process(RecordDebitCommand cmd) {
    throw new RuntimeException("not yet implemented");
  }

  public void apply(MoneyTransferDebitRecordedEvent event) {
    throw new RuntimeException("not yet implemented");
  }

  public List<Event> process(RecordCreditCommand cmd) {
    throw new RuntimeException("not yet implemented");
  }

  public void apply(MoneyTransferCreditRecordedEvent event) {
    throw new RuntimeException("not yet implemented");
  }

  public List<Event> process(RecordDebitFailedDueToInsufficientFundsCommand cmd) {
    throw new RuntimeException("not yet implemented");
  }

  public void apply(MoneyTransferFailedDebitRecordedEvent event) {
    this.state = MoneyTransferState.FAILED_DUE_TO_INSUFFICIENT_FUNDS;
  }

  public MoneyTransferState getState() {
    return state;
  }
}
