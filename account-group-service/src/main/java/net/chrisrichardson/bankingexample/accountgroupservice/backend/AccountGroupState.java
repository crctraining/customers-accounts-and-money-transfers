package net.chrisrichardson.bankingexample.accountgroupservice.backend;

public enum AccountGroupState {
  CREATE_PENDING_PARENT_VALIDATION, MISSING_PARENT, CREATION_FAILED, NEW, CREATED
}
