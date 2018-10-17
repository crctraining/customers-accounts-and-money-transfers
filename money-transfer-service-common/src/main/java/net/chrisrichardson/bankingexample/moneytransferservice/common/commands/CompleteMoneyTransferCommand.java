package net.chrisrichardson.bankingexample.moneytransferservice.common.commands;

import io.eventuate.tram.commands.common.Command;

public class CompleteMoneyTransferCommand implements Command {

  private long moneyTransferId;

  public CompleteMoneyTransferCommand() {
  }

  public CompleteMoneyTransferCommand(long moneyTransferId) {
    this.moneyTransferId = moneyTransferId;
  }

  public long getMoneyTransferId() {
    return moneyTransferId;
  }

  public void setMoneyTransferId(long moneyTransferId) {
    this.moneyTransferId = moneyTransferId;
  }
}
