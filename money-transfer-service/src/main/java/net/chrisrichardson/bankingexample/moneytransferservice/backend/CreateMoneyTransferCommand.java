package net.chrisrichardson.bankingexample.moneytransferservice.backend;


import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;

public class CreateMoneyTransferCommand implements MoneyTransferCommand {
  private MoneyTransferInfo moneyTransferInfo;

  public CreateMoneyTransferCommand(MoneyTransferInfo moneyTransferInfo) {
    this.moneyTransferInfo = moneyTransferInfo;
  }

  public MoneyTransferInfo getMoneyTransferInfo() {
    return moneyTransferInfo;
  }
}
