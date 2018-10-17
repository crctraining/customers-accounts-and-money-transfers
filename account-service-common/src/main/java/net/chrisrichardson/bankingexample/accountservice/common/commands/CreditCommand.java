package net.chrisrichardson.bankingexample.accountservice.common.commands;

import io.eventuate.tram.commands.common.Command;
import net.chrisrichardson.bankingexample.commondomain.Money;

public class CreditCommand implements Command {

  private long accountId;
  private Money amount;

  public CreditCommand() {
  }

  public CreditCommand(long accountId, Money amount) {
    this.accountId = accountId;
    this.amount = amount;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }


  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }

}
