package net.chrisrichardson.bankingexample.accountservice.common.events;

import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class AccountOpenedEvent implements AccountEvent {
  private AccountInfo accountInfo;

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  public AccountOpenedEvent() {
  }

  public AccountOpenedEvent(AccountInfo accountInfo) {
    this.accountInfo = accountInfo;
  }

  public AccountInfo getAccountInfo() {
    return accountInfo;
  }
}
