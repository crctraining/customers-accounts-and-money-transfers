package net.chrisrichardson.bankingexample.endtoendtest;

import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.web.CreateAccountResponse;
import net.chrisrichardson.bankingexample.commondomain.Money;
import net.chrisrichardson.bankingexample.customerservice.common.Address;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import net.chrisrichardson.bankingexample.customerservice.common.Name;
import net.chrisrichardson.bankingexample.customerservice.web.CreateCustomerResponse;
import net.chrisrichardson.bankingexample.customerviewservice.backend.CustomerView;
import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferState;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.web.CreateMoneyTransferResponse;
import net.chrisrichardson.bankingexample.moneytransferservice.web.GetMoneyTransferResponse;
import net.chrisrichardson.bankingexample.testutil.Eventually;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EndToEndTest {

  private RestTemplate restTemplate = new RestTemplate();

  private static String baseUrl(int port, String prefix, String... path) {
    Assert.notNull(System.getenv("SERVICE_HOST_IP"), "SERVICE_HOST_IP must be set");
    StringBuilder sb = new StringBuilder("http://" + System.getenv("SERVICE_HOST_IP") + ":" + port + "/api" + prefix);
    for (String x : path) {
      sb.append("/").append(x);
    }
    return sb.toString();
  }


  private static String getenv(String name, String defaultValue) {
    return Optional.ofNullable(System.getenv(name)).orElse(defaultValue);
  }

  private int API_GATEWAY_PORT = Integer.parseInt(getenv("API_GATEWAY_PORT", "8080"));


  private String customerUrl(String... path) {
    return baseUrl(API_GATEWAY_PORT, "/customers", path);
  }

  private String customerViewUrl(String... path) {
    return baseUrl(API_GATEWAY_PORT, "/customerview", path);
  }

  private String moneyTransferUrl(String... path) {
    return baseUrl(API_GATEWAY_PORT, "/moneytransfers", path);
  }

  private String accountUrl(String... path) {
    return baseUrl(API_GATEWAY_PORT, "/accounts", path);
  }

  private Money fromInitialBalance = new Money("12.34");
  private Money toInitialBalance = new Money("100.86");
  private Money transferAmount = new Money("1.24");
  private Money fromFinalBalance = new Money("12.34").subtract(transferAmount);
  private Money toFinalBalance = new Money("100.86").add(transferAmount);

  @Test
  public void createAccountWithBadCustomerId() {
    try {
      createAccount("bad-customer-id", fromInitialBalance, "checking");
      fail("Expected exception");
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() != HttpStatus.BAD_REQUEST)
        throw e;
    }

  }

  @Test
  public void shouldWork() {

    // Given a customer

    String customerId = createCustomer();

    // and a fromAccount

    String fromAccountId = createAccount(customerId, fromInitialBalance, "checking");

    // and a toAccount

    String toAccountId = createAccount(customerId, toInitialBalance, "saving");

    // when I transfer money

    String moneyTransferId = createMoneyTransfer(fromAccountId, toAccountId, transferAmount);

    // then the transfer completes

    assertTranferCompleted(moneyTransferId);

    // and the from account is debited

    assertAccountBalance(fromAccountId, fromFinalBalance);

    // and the to account is credited

    assertAccountBalance(toAccountId, toFinalBalance);

    assertCustomerViewUpdated(customerId, toAccountId, fromAccountId, moneyTransferId);
  }


  private String createCustomer() {

    CustomerInfo customerInfo = new CustomerInfo(
            new Name("John", "Doe"), "510-555-1212",
            new Address("1 high street", null, "Oakland", "CA", "94719"),
            "xxx-yy-zzz");
    ResponseEntity<CreateCustomerResponse> createResponse = restTemplate.postForEntity(customerUrl(), customerInfo, CreateCustomerResponse.class);
    assertEquals(HttpStatus.OK, createResponse.getStatusCode());
    CreateCustomerResponse customerResponse = createResponse.getBody();

    return customerResponse.getId();
  }

  private void assertAccountBalance(String accountId, Money expectedBalance) {
    Eventually.eventually(() -> {
      assertEquals(expectedBalance, getBalance(accountId));
    });
  }

  private void assertTranferCompleted(String moneyTransferId) {
    Eventually.eventually(() -> {
      ResponseEntity<GetMoneyTransferResponse> response = restTemplate.getForEntity(moneyTransferUrl(moneyTransferId),
              GetMoneyTransferResponse.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(MoneyTransferState.COMPLETED, response.getBody().getState());

    });
  }

  private String createMoneyTransfer(String fromAccountId, String toAccountId, Money transferAmount) {
    MoneyTransferInfo moneyTransferInfo = new MoneyTransferInfo(fromAccountId, toAccountId, transferAmount);
    ResponseEntity<CreateMoneyTransferResponse> createMoneyTransferResponse = restTemplate.postForEntity(moneyTransferUrl(),
            moneyTransferInfo,
            CreateMoneyTransferResponse.class);
    assertEquals(HttpStatus.OK, createMoneyTransferResponse.getStatusCode());
    CreateMoneyTransferResponse moneyTransferResponse = createMoneyTransferResponse.getBody();
    return moneyTransferResponse.getId();
  }

  private String createAccount(String customerId, Money fromInitialBalance, String fromAccountTitle) {
    ResponseEntity<CreateAccountResponse> createAccount1 = restTemplate.postForEntity(accountUrl(),
            new AccountInfo(customerId, fromAccountTitle, fromInitialBalance),
            CreateAccountResponse.class);
    assertEquals(HttpStatus.OK, createAccount1.getStatusCode());
    CreateAccountResponse accountBody1 = createAccount1.getBody();
    return accountBody1.getId();
  }

  private Money getBalance(String accountId) {
    ResponseEntity<AccountInfo> response = restTemplate.getForEntity(accountUrl(accountId),
            AccountInfo.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    return response.getBody().getBalance();
  }

  private void assertCustomerViewUpdated(String customerId, String toAccountId, String fromAccountId, String moneyTransferId) {
    Eventually.eventually(() -> {

      ResponseEntity<CustomerView> response = restTemplate.getForEntity(customerViewUrl(customerId), CustomerView.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      CustomerView customerView = response.getBody();
      assertNotNull(customerView.getAccounts().get(toAccountId));
      assertNotNull(customerView.getAccounts().get(fromAccountId));

    });
  }
}
