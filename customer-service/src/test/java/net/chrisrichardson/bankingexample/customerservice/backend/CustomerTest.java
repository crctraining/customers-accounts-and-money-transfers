package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.testutil.AggregateTest;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest extends AggregateTest<Customer, CustomerCommand> {

  public CustomerTest() {
    super(Customer.class);
  }


  @Test
  public void shouldCreate() {
    CustomerInfo customerInfo = CustomerMother.makeCustomer();

    create(new CreateCustomerCommand(customerInfo));

    assertEquals(customerInfo, aggregate.getCustomerInfo());
  }
}