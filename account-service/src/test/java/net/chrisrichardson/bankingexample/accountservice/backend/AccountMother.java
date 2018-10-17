package net.chrisrichardson.bankingexample.accountservice.backend;

import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.commondomain.Money;

public class AccountMother {

  public static long customerId = 101L;
  public static Money initialBalance = new Money("12.34");
  public static Long accountId = 102L;

  public static Money amount = new Money("12.45");

  public static AccountInfo makeAccount(long customerId) {
    return new AccountInfo(customerId, "Checking", initialBalance);
  }

  public static AccountInfo makeAccount() {
    return makeAccount(customerId);
  }
}
