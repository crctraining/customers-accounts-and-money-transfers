package net.chrisrichardson.bankingexample.accountservice.backend;

import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.commondomain.Money;

public class AccountMother {

  public static AccountInfo makeAccount(String customerId) {
    return new AccountInfo(customerId, "Checking", new Money("12.34"));
  }
}
