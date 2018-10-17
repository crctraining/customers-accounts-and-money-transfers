package net.chrisrichardson.bankingexample.moneytransferservice.common;

import net.chrisrichardson.bankingexample.commondomain.Money;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
public class MoneyTransferInfo {

  private long fromAccountId;
  private long toAccountId;

  @Embedded
  private Money amount;

  public MoneyTransferInfo() {
  }

  public MoneyTransferInfo(long fromAccountId, long toAccountId, Money amount) {
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

  public long getFromAccountId() {
    return fromAccountId;
  }

  public void setFromAccountId(long fromAccountId) {
    this.fromAccountId = fromAccountId;
  }

  public long getToAccountId() {
    return toAccountId;
  }

  public void setToAccountId(long toAccountId) {
    this.toAccountId = toAccountId;
  }

  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }
}
