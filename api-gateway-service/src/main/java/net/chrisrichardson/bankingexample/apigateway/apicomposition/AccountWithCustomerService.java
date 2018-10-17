package net.chrisrichardson.bankingexample.apigateway.apicomposition;

import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountWithCustomerService {

  private final AccountServiceProxy accountServiceProxy;
  private final CustomerServiceProxy customerServiceProxy;

  public AccountWithCustomerService(AccountServiceProxy accountServiceProxy, CustomerServiceProxy customerServiceProxy) {
    this.accountServiceProxy = accountServiceProxy;
    this.customerServiceProxy = customerServiceProxy;
  }

  public Optional<AccountWithCustomer> getAccountWithCustomer(long accountId) {
    return accountServiceProxy.getAccount(accountId).map(account -> {
      long customerId = account.getAccountInfo().getCustomerId();
      CustomerInfo customer = customerServiceProxy.getCustomer(customerId);
      return new AccountWithCustomer(account, customer);
    });
  }
}
