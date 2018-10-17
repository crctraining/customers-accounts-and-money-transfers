package net.chrisrichardson.bankingexample.moneytransferservice.common;

public class CreateMoneyTransferResponse {
  private long id;

  public CreateMoneyTransferResponse() {
  }

  public CreateMoneyTransferResponse(Long id) {

    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
