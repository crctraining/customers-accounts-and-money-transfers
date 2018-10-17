package net.chrisrichardson.bankingexample.accountgroupservice.common.web;

public class AccountGroupInfo {

  private String parentId;
  private String name;

  private AccountGroupInfo() {
  }

  public AccountGroupInfo(String parentId, String name) {
    this.parentId = parentId;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getParentId() {
    return parentId;
  }

}
