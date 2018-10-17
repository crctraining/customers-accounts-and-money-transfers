package net.chrisrichardson.bankingexample.accountservice.common;

public class GetAccountResponse {
  private AccountInfo accountInfo;
  private AccountState state;

  private GetAccountResponse() {
  }

  public GetAccountResponse(AccountInfo accountInfo, AccountState state) {
    this.accountInfo = accountInfo;
    this.state = state;
  }

  public AccountInfo getAccountInfo() {
    return accountInfo;
  }

  public void setAccountInfo(AccountInfo accountInfo) {
    this.accountInfo = accountInfo;
  }

  public AccountState getState() {
    return state;
  }

  public void setState(AccountState state) {
    this.state = state;
  }
}
