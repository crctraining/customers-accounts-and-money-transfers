package net.chrisrichardson.bankingexample.accountgroupservice.common.web;


public class GetAccountGroupResponse {

  private String state;
  private String name;

  private GetAccountGroupResponse() {
  }

  public GetAccountGroupResponse(String state, String name) {
    this.state = state;
    this.name = name;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {

    this.state = state;
  }
}
