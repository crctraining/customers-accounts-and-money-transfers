package net.chrisrichardson.bankingexample.moneytransferservice.common;

import net.chrisrichardson.bankingexample.commondomain.Money;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class MoneyTransferInfo {

  private String fromAccountId;
  private String toAccountId;
  private Money amount;

  public MoneyTransferInfo() {
  }

  public MoneyTransferInfo(String fromAccountId, String toAccountId, Money amount) {
    this.fromAccountId = fromAccountId;
    this.toAccountId = toAccountId;
    this.amount = amount;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  public String getFromAccountId() {
    return fromAccountId;
  }

  public void setFromAccountId(String fromAccountId) {
    this.fromAccountId = fromAccountId;
  }

  public String getToAccountId() {
    return toAccountId;
  }

  public void setToAccountId(String toAccountId) {
    this.toAccountId = toAccountId;
  }

  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }
}
