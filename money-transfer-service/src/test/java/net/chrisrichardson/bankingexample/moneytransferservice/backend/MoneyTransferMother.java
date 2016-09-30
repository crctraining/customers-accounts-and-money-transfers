package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import net.chrisrichardson.bankingexample.commondomain.Money;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;

public class MoneyTransferMother {

  public static MoneyTransferInfo makeMoneyTransfer() {
    return new MoneyTransferInfo("account-a", "account-b", new Money("1.24"));
  }
}
