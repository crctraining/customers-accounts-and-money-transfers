package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferState;

import javax.persistence.*;

@Entity
@Table(name = "transfers")
@Access(AccessType.FIELD)
public class MoneyTransfer  {

  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  private MoneyTransferState state;

  @Embedded
  private MoneyTransferInfo moneyTransferInfo;

  private MoneyTransfer() {
  }


  public MoneyTransfer(MoneyTransferInfo moneyTransferInfo) {
    this.moneyTransferInfo = moneyTransferInfo;
    this.state = MoneyTransferState.IN_PROGRESS;
  }

  public MoneyTransferInfo getMoneyTransferInfo() {
    return moneyTransferInfo;
  }

  public MoneyTransferState getState() {
    return state;
  }


  public Long getId() {
    return id;
  }
  void setId(long id) {
    this.id = id;
  }

  public void complete() {
    switch (state) {
      case IN_PROGRESS:
        this.state = MoneyTransferState.COMPLETED;
        break;
      default:
        throw new RuntimeException("Dont know how recordCredit in this state: " + state);
    }
  }

  public void cancel() {
    switch (state) {
      case IN_PROGRESS:
        this.state = MoneyTransferState.FAILED_DUE_TO_INSUFFICIENT_FUNDS;
        break;
      default:
        throw new RuntimeException("Dont know how recordInsufficientFunds in this state: " + state);
    }
  }

}
