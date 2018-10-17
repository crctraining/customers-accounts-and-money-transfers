package net.chrisrichardson.bankingexample.customerviewservice.backend;

import io.eventuate.javaclient.spring.jdbc.IdGenerator;
import io.eventuate.javaclient.spring.jdbc.IdGeneratorImpl;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.commondomain.Money;
import net.chrisrichardson.bankingexample.customerservice.common.Address;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import net.chrisrichardson.bankingexample.customerservice.common.Name;
import net.chrisrichardson.bankingexample.customerviewservice.common.CustomerView;
import org.junit.Before;
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
@SpringBootTest(classes = CustomerViewServiceIntegrationTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerViewServiceIntegrationTest {

  @Autowired
  private CustomerViewService customerViewService;


  private IdGenerator idGenerator = new IdGeneratorImpl();
  private long customerId;
  private String customerIdS;
  private long accountId;
  private String accountIdS;
  private String createAccount1EventId;
  private String transactionId;

  @Before
  public void setUp() {
    customerId = System.currentTimeMillis();
    customerIdS = Long.toString(customerId);
    accountId = System.currentTimeMillis();
    accountIdS = Long.toString(accountId);

    createAccount1EventId = idGenerator.genId().asString();
    transactionId = idGenerator.genId().asString();
  }

  @Test
  public void shouldDoSomething() {

    CustomerInfo customerInfo = new CustomerInfo(
            new Name("John", "Doe"), "510-555-1212",
            new Address("1 high street", null, "Oakland", "CA", "94719"),
            "xxx-yy-zzz");

    customerViewService.createCustomer(customerIdS, customerInfo);
    customerViewService.createCustomer(customerIdS, customerInfo);


    AccountInfo accountInfo1 = new AccountInfo(customerId, "Checking", new Money("4.56"));

    customerViewService.openAccount(createAccount1EventId, accountIdS, accountInfo1);

    customerViewService.debitAccount(idGenerator.genId().asString(), accountIdS, customerIdS, new Money(1), new Money("3.56"), transactionId);

    Optional<CustomerView> customerView = customerViewService.findByCustomerId(customerIdS);
    assertTrue(customerView.isPresent());
    assertEquals(customerInfo.getSsn(), customerView.get().getCustomerInfo().getSsn());
  }

  @Test
  public void shouldDoSomethingDifferentOrder() {

    AccountInfo accountInfo1 = new AccountInfo(customerId, "Checking", new Money("4.56"));

    customerViewService.openAccount(createAccount1EventId, accountIdS, accountInfo1);

    CustomerInfo customerInfo = new CustomerInfo(
            new Name("John", "Doe"), "510-555-1212",
            new Address("1 high street", null, "Oakland", "CA", "94719"),
            "xxx-yy-zzz");

    customerViewService.createCustomer(customerIdS, customerInfo);

    customerViewService.debitAccount(idGenerator.genId().asString(), accountIdS, customerIdS, new Money(1), new Money("3.56"), transactionId);

    Optional<CustomerView> customerView = customerViewService.findByCustomerId(customerIdS);
    assertTrue(customerView.isPresent());
    assertEquals(customerInfo.getSsn(), customerView.get().getCustomerInfo().getSsn());
  }

}
