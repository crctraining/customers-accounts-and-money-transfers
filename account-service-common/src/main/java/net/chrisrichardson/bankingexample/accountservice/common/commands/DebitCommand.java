package net.chrisrichardson.bankingexample.accountservice.common.commands;

import io.eventuate.tram.commands.common.Command;
import net.chrisrichardson.bankingexample.commondomain.Money;

public class DebitCommand implements Command {

  private Money amount;
  private long accountId;

  public DebitCommand() {
  }

  public DebitCommand(long accountId, Money amount) {
    this.accountId = accountId;
    this.amount = amount;
  }

  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }
}
