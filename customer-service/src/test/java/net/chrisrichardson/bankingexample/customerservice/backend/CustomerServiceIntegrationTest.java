package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerServiceIntegrationTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerServiceIntegrationTest {

  @Autowired
  private CustomerService customerService;

  @Test
  public void shouldSaveAndLoadCustomer() {

    CustomerInfo customerInfo = CustomerMother.makeCustomer();

    Customer savedCustomer = customerService.createCustomer(customerInfo);

    Optional<Customer> loadedCustomer = customerService.findCustomer(savedCustomer.getId());

    assertTrue(loadedCustomer.isPresent());

    assertEquals(customerInfo, loadedCustomer.get().getCustomerInfo());
  }
}