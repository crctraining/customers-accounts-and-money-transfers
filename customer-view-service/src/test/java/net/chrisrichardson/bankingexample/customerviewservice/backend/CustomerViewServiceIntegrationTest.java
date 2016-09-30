package net.chrisrichardson.bankingexample.customerviewservice.backend;

import io.eventuate.Int128;
import io.eventuate.javaclient.spring.jdbc.IdGenerator;
import io.eventuate.javaclient.spring.jdbc.IdGeneratorImpl;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.commondomain.Money;
import net.chrisrichardson.bankingexample.customerservice.common.Address;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import net.chrisrichardson.bankingexample.customerservice.common.Name;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CustomerViewServiceIntegrationTestConfiguration.class)
@IntegrationTest
public class CustomerViewServiceIntegrationTest {

  @Autowired
  private CustomerViewService customerViewService;


  private IdGenerator idGenerator = new IdGeneratorImpl();

  @Test
  public void shouldDoSomething() {
    String customerId = "customerId-" + System.currentTimeMillis();

    CustomerInfo customerInfo = new CustomerInfo(
            new Name("John", "Doe"), "510-555-1212",
            new Address("1 high street", null, "Oakland", "CA", "94719"),
            "xxx-yy-zzz");

    customerViewService.createCustomer(customerId, customerInfo);
    customerViewService.createCustomer(customerId, customerInfo);

    String accountId = "account-" + System.currentTimeMillis();
    Int128 createAccount1EventId = idGenerator.genId();
    String transactionId = idGenerator.genId().asString();

    AccountInfo accountInfo1 = new AccountInfo(customerId, "Checking", new Money("4.56"));

    customerViewService.openAccount(createAccount1EventId, accountId, accountInfo1);

    customerViewService.debitAccount(idGenerator.genId(), accountId, customerId, new Money(1), new Money("3.56"), transactionId);

    CustomerView customerView = customerViewService.findByCustomerId(customerId);
    assertNotNull(customerView);
    assertEquals(customerInfo.getSsn(), customerView.getCustomerInfo().getSsn());
  }

  @Test
  public void shouldDoSomethingDifferentOrder() {
    String customerId = "customerId-" + System.currentTimeMillis();
    String accountId = "account-" + System.currentTimeMillis();
    Int128 createAccount1EventId = idGenerator.genId();
    String transactionId = idGenerator.genId().asString();

    AccountInfo accountInfo1 = new AccountInfo(customerId, "Checking", new Money("4.56"));

    customerViewService.openAccount(createAccount1EventId, accountId, accountInfo1);

    CustomerInfo customerInfo = new CustomerInfo(
            new Name("John", "Doe"), "510-555-1212",
            new Address("1 high street", null, "Oakland", "CA", "94719"),
            "xxx-yy-zzz");

    customerViewService.createCustomer(customerId, customerInfo);

    customerViewService.debitAccount(idGenerator.genId(), accountId, customerId, new Money(1), new Money("3.56"), transactionId);

    CustomerView customerView = customerViewService.findByCustomerId(customerId);
    assertNotNull(customerView);
    assertEquals(customerInfo.getSsn(), customerView.getCustomerInfo().getSsn());
  }

}
