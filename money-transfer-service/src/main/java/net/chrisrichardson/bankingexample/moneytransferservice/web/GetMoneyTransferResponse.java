package net.chrisrichardson.bankingexample.moneytransferservice.web;

import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferState;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;

public class GetMoneyTransferResponse {
  private MoneyTransferState state;
  private MoneyTransferInfo moneyTransferInfo;

  public GetMoneyTransferResponse() {
  }

  public GetMoneyTransferResponse(MoneyTransferState state, MoneyTransferInfo moneyTransferInfo) {
    this.state = state;
    this.moneyTransferInfo = moneyTransferInfo;
  }

  public MoneyTransferState getState() {
    return state;
  }

  public void setState(MoneyTransferState state) {
    this.state = state;
  }

  public MoneyTransferInfo getMoneyTransferInfo() {
    return moneyTransferInfo;
  }

  public void setMoneyTransferInfo(MoneyTransferInfo moneyTransferInfo) {
    this.moneyTransferInfo = moneyTransferInfo;
  }
}
