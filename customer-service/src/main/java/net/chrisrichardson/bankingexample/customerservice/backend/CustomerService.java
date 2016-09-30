package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import io.eventuate.sync.AggregateRepository;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final AggregateRepository<Customer, CustomerCommand> customerRepository;

  public CustomerService(AggregateRepository<Customer, CustomerCommand> customerRepository) {
    this.customerRepository = customerRepository;
  }

  public EntityWithIdAndVersion<Customer> createCustomer(CustomerInfo customerInfo) {
    return customerRepository.save(new CreateCustomerCommand(customerInfo));
  }

  public EntityWithMetadata<Customer> findCustomer(String id) {
    return customerRepository.find(id);
  }
}
