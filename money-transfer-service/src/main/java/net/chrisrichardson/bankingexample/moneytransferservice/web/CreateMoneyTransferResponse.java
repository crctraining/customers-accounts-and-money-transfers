package net.chrisrichardson.bankingexample.moneytransferservice.web;

public class CreateMoneyTransferResponse {
  private String id;

  public CreateMoneyTransferResponse() {
  }

  public CreateMoneyTransferResponse(String id) {

    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
