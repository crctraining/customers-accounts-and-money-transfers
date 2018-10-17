package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import net.chrisrichardson.bankingexample.commondomain.Money;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;

public class MoneyTransferMother {
  public static long moneyTransferId = 106L;

  public static MoneyTransferInfo moneyTransferInfo = new MoneyTransferInfo(101, 102, new Money("1.24"));

  public static MoneyTransferInfo makeMoneyTransfer() {
    return moneyTransferInfo;
  }
}
