package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import net.chrisrichardson.bankingexample.customerservice.common.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

  private final CustomerRepository customerRepository;
  private DomainEventPublisher domainEventPublisher;

  public CustomerService(CustomerRepository customerRepository, DomainEventPublisher domainEventPublisher) {
    this.customerRepository = customerRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  public Customer createCustomer(CustomerInfo customerInfo) {
    Customer customer = new Customer(customerInfo);
    customerRepository.save(customer);
    domainEventPublisher.publish(Customer.class, customer.getId(), Collections.singletonList(new CustomerCreatedEvent(customerInfo)));
    return customer;
  }

  public Optional<Customer> findCustomer(long id) {
    return customerRepository.findById(id);
  }

  public void validateCustomer(long accountId, long customerId) {
    Optional<Customer> customer = customerRepository.findById(customerId);
    domainEventPublisher.publish(Customer.class,
            customerId,
            Collections.singletonList(customer.map(c -> (CustomerEvent)new CustomerValidatedEvent(accountId)).orElseGet(() -> new CustomerValidationFailedEvent(accountId))));
  }
}
