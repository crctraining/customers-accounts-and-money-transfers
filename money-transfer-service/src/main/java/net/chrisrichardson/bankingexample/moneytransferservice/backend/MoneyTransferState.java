package net.chrisrichardson.bankingexample.moneytransferservice.backend;

public enum MoneyTransferState {
  NEW, DEBITED, COMPLETED, FAILED_DUE_TO_INSUFFICIENT_FUNDS
}

