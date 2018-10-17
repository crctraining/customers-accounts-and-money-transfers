package net.chrisrichardson.bankingexample.accountservice.common;

public class CreateAccountResponse {
  private long id;

  public CreateAccountResponse() {
  }

  public CreateAccountResponse(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
