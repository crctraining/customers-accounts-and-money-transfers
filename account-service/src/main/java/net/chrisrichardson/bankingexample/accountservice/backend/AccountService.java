package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.sync.AggregateRepository;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AccountService {

  private CustomerRepository customerRepository;
  private AggregateRepository<Account, AccountCommand> accountAggregateRepository;

  public AccountService(CustomerRepository customerRepository,
                        AggregateRepository<Account, AccountCommand> accountAggregateRepository) {
    this.customerRepository = customerRepository;
    this.accountAggregateRepository = accountAggregateRepository;
  }


  public EntityWithIdAndVersion<Account> createAccount(AccountInfo accountInfo) {
    // validate-customerId {
    CustomerInfo customerInfo = customerRepository.findCustomerById(accountInfo.getCustomerId());
    Assert.notNull(customerInfo);
    // validate-customerId }
    return accountAggregateRepository.save(new OpenAccountCommand(accountInfo));
  }

  public Account findAccount(String id) {
    return accountAggregateRepository.find(id).getEntity();
  }
}
