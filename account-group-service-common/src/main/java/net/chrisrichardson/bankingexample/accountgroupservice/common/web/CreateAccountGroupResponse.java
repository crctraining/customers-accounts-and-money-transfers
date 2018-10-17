package net.chrisrichardson.bankingexample.accountgroupservice.common.web;

public class CreateAccountGroupResponse {
  private String accountGroupId;

  public CreateAccountGroupResponse() {
  }

  public CreateAccountGroupResponse(String accountGroupId) {
    this.accountGroupId = accountGroupId;
  }

  public String getAccountGroupId() {
    return accountGroupId;
  }

  public void setAccountGroupId(String accountGroupId) {
    this.accountGroupId = accountGroupId;
  }
}
