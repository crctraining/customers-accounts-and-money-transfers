package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerCreatedEvent;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;

import java.util.List;

import static io.eventuate.EventUtil.events;

public class Customer extends ReflectiveMutableCommandProcessingAggregate<Customer, CustomerCommand> {

  private CustomerInfo customerInfo;

  public Customer() {
  }

  public Customer(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
  }

  public CustomerInfo getCustomerInfo() {
    return customerInfo;
  }

  public void setCustomerInfo(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
  }

  public List<Event> process(CreateCustomerCommand cmd) {
    return events(new CustomerCreatedEvent(cmd.getCustomerInfo()));
  }

  public void apply(CustomerCreatedEvent event) {
    this.customerInfo = event.getCustomerInfo();
  }
}
