package net.chrisrichardson.bankingexample.moneytransferservice.sagas;

import net.chrisrichardson.bankingexample.accountservice.common.commands.CreditCommand;
import net.chrisrichardson.bankingexample.accountservice.common.commands.DebitCommand;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.CancelMoneyTransferCommand;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.CompleteMoneyTransferCommand;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferMoneySagaState {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private long moneyTransferId;
  private MoneyTransferInfo moneyTransferInfo;

  private TransferMoneySagaState() {
  }

  public TransferMoneySagaState(long moneyTransferId, MoneyTransferInfo moneyTransferInfo) {
    this.moneyTransferId = moneyTransferId;
    this.moneyTransferInfo = moneyTransferInfo;
  }


  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }


  public Long getMoneyTransferId() {
    return moneyTransferId;
  }

  public void setMoneyTransferId(Long moneyTransferId) {
    this.moneyTransferId = moneyTransferId;
  }

  public MoneyTransferInfo getMoneyTransferInfo() {
    return moneyTransferInfo;
  }

  public void setMoneyTransferInfo(MoneyTransferInfo moneyTransferInfo) {
    this.moneyTransferInfo = moneyTransferInfo;
  }


  public DebitCommand makeDebitAccountCommand() {
    return new DebitCommand(moneyTransferInfo.getFromAccountId(), moneyTransferInfo.getAmount());
  }

  public CreditCommand makeReverseDebitCommand() {
    return new CreditCommand(moneyTransferInfo.getFromAccountId(), moneyTransferInfo.getAmount());
  }

  public CreditCommand makeCreditAccountCommand() {
    return new CreditCommand(moneyTransferInfo.getToAccountId(), moneyTransferInfo.getAmount());
  }


  public CancelMoneyTransferCommand makeCancelMoneyTransferCommand() {
    return new CancelMoneyTransferCommand(moneyTransferId);
  }

  public CompleteMoneyTransferCommand makeCompleteMoneyTransferCommand() {
    return new CompleteMoneyTransferCommand(moneyTransferId);
  }


}
