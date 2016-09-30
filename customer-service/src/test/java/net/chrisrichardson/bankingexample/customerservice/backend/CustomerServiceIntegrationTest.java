package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CustomerJpaIntegrationTestConfiguration.class)
@IntegrationTest
public class CustomerServiceIntegrationTest {

  @Autowired
  private CustomerService customerService;

  @Test
  public void shouldSaveAndLoadCustomer() {

    CustomerInfo customerInfo = CustomerMother.makeCustomer();

    EntityWithIdAndVersion<Customer> savedCustomer = customerService.createCustomer(customerInfo);

    EntityWithMetadata<Customer> loadedCustomer = customerService.findCustomer(savedCustomer.getEntityId());

    assertNotNull(loadedCustomer);

    assertEquals(customerInfo, loadedCustomer.getEntity().getCustomerInfo());
  }
}