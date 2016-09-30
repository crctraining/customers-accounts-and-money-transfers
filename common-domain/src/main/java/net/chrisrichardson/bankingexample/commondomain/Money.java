package net.chrisrichardson.bankingexample.commondomain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.math.BigDecimal;

public class Money {

  private long amount;


  public Money() {
  }


  public Money(long amount) {
    this.amount = amount;
  }

  public Money(String amount) {
    this.amount = new BigDecimal(amount).multiply(new BigDecimal(100)).longValue();
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public Money subtract(Money other) {
    return new Money(amount - other.getAmount());
  }

  public Money add(Money other) {
    return new Money(amount + other.getAmount());
  }

  public boolean isGreaterOrEqualThan(Money otherAmount) {
    return this.amount >= otherAmount.getAmount();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
