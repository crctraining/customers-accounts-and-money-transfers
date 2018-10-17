package net.chrisrichardson.bankingexample.endtoendtest;

import net.chrisrichardson.bankingexample.accountgroupservice.common.web.AccountGroupInfo;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.CreateAccountGroupResponse;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.GetAccountGroupResponse;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.common.AccountState;
import net.chrisrichardson.bankingexample.accountservice.common.CreateAccountResponse;
import net.chrisrichardson.bankingexample.accountservice.common.GetAccountResponse;
import net.chrisrichardson.bankingexample.commondomain.Money;
import net.chrisrichardson.bankingexample.customerservice.common.Address;
import net.chrisrichardson.bankingexample.customerservice.common.CreateCustomerResponse;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import net.chrisrichardson.bankingexample.customerservice.common.Name;
import net.chrisrichardson.bankingexample.customerviewservice.common.AccountView;
import net.chrisrichardson.bankingexample.customerviewservice.common.CustomerView;
import net.chrisrichardson.bankingexample.moneytransferservice.common.CreateMoneyTransferResponse;
import net.chrisrichardson.bankingexample.moneytransferservice.common.GetMoneyTransferResponse;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferState;
import net.chrisrichardson.bankingexample.testutil.Eventually;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EndToEndTest {

  private RestTemplate restTemplate = new RestTemplate();


  private static String baseUrl(String prefix, String... path) {
    String apiGatewayUrl = System.getenv("API_GATEWAY_URL");
    Assert.notNull(apiGatewayUrl, "API_GATEWAY_URL must be set");
    StringBuilder sb = new StringBuilder(apiGatewayUrl + "/api" + prefix);
    for (String x : path) {
      sb.append("/").append(x);
    }
    return sb.toString();
  }

  private String customerUrl(String... path) {
    return baseUrl("/customers", path);
  }

  private String customerViewUrl(String... path) {
    return baseUrl("/customerview", path);
  }

  private String moneyTransferUrl(String... path) {
    return baseUrl("/moneytransfers", path);
  }

  private String accountUrl(String... path) {
    return baseUrl("/accounts", path);
  }

  private String accountGroupsUrl(String... path) {
    return baseUrl("/accountgroups", path);
  }

  private Money fromInitialBalance = new Money("12.34");
  private Money toInitialBalance = new Money("100.86");
  private Money transferAmount = new Money("1.24");
  private Money fromFinalBalance = new Money("12.34").subtract(transferAmount);
  private Money toFinalBalance = new Money("100.86").add(transferAmount);


  @Test
  public void shouldWork() {

    // Given a customer

    long customerId = createCustomer();

    long fromAccountId = createAccount(customerId, fromInitialBalance, "checking");

    assertAccountOpen(fromAccountId);

    assertEquals(fromInitialBalance, getBalance(fromAccountId));

    long toAccountId = createAccount(customerId, toInitialBalance, "saving");

    assertAccountOpen(toAccountId);
    assertEquals(toInitialBalance, getBalance(toAccountId));

    String accountGroupId = createAccountGroup(null);

    assertAccountGroupCreated(accountGroupId);

    String childAccountGroupId = createAccountGroup(accountGroupId);

    assertAccountGroupCreated(childAccountGroupId);


    long moneyTransferId = createMoneyTransfer(fromAccountId, toAccountId, transferAmount);

    assertTransferCompleted(moneyTransferId);

    assertEquals(fromFinalBalance, getBalance(fromAccountId));

    assertEquals(toFinalBalance, getBalance(toAccountId));


    assertCustomerViewUpdated(customerId, fromAccountId, fromFinalBalance, toAccountId, toFinalBalance, moneyTransferId);

  }

  private void assertAccountGroupCreated(String accountGroupId) {
    Eventually.eventually(() -> {
      assertEquals("CREATED", getAccountGroup(accountGroupId).getState());
    });
  }

  private GetAccountGroupResponse getAccountGroup(String accountGroupId) {
    ResponseEntity<GetAccountGroupResponse> response = restTemplate.getForEntity(accountGroupsUrl(accountGroupId),
            GetAccountGroupResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    return response.getBody();

  }

  private String createAccountGroup(String parentId) {
    ResponseEntity<CreateAccountGroupResponse> agr = restTemplate.postForEntity(accountGroupsUrl(),
            new AccountGroupInfo(parentId, "My Account Grop"),
            CreateAccountGroupResponse.class);
    assertEquals(HttpStatus.OK, agr.getStatusCode());
    return agr.getBody().getAccountGroupId();

  }

  private void assertAccountOpen(long accountId) {
    Eventually.eventually(() -> {
      assertEquals(AccountState.OPEN, getAccount(accountId).getState());
    });

  }

  /*
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
  */

  private void assertTransferCompleted(long moneyTransferId) {
    Eventually.eventually(() -> {
      ResponseEntity<GetMoneyTransferResponse> response = restTemplate.getForEntity(moneyTransferUrl(Long.toString(moneyTransferId)),
              GetMoneyTransferResponse.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(MoneyTransferState.COMPLETED, response.getBody().getState());

    });
  }

  private long createMoneyTransfer(long fromAccountId, long toAccountId, Money transferAmount) {
    MoneyTransferInfo moneyTransferInfo = new MoneyTransferInfo(fromAccountId, toAccountId, transferAmount);
    ResponseEntity<CreateMoneyTransferResponse> createMoneyTransferResponse = restTemplate.postForEntity(moneyTransferUrl(),
            moneyTransferInfo,
            CreateMoneyTransferResponse.class);
    assertEquals(HttpStatus.OK, createMoneyTransferResponse.getStatusCode());
    CreateMoneyTransferResponse moneyTransferResponse = createMoneyTransferResponse.getBody();
    return moneyTransferResponse.getId();
  }

  private long createCustomer() {

    CustomerInfo customerInfo = new CustomerInfo(
            new Name("John", "Doe"), "510-555-1212",
            new Address("1 high street", null, "Oakland", "CA", "94719"),
            "xxx-yy-zzz");
    ResponseEntity<CreateCustomerResponse> createResponse = restTemplate.postForEntity(customerUrl(), customerInfo, CreateCustomerResponse.class);
    assertEquals(HttpStatus.OK, createResponse.getStatusCode());
    CreateCustomerResponse customerResponse = createResponse.getBody();

    return customerResponse.getId();
  }

  private long createAccount(long customerId, Money fromInitialBalance, String fromAccountTitle) {
    ResponseEntity<CreateAccountResponse> createAccount1 = restTemplate.postForEntity(accountUrl(),
            new AccountInfo(customerId, fromAccountTitle, fromInitialBalance),
            CreateAccountResponse.class);
    assertEquals(HttpStatus.OK, createAccount1.getStatusCode());
    CreateAccountResponse accountBody1 = createAccount1.getBody();
    return accountBody1.getId();
  }

  private Money getBalance(long accountId) {
    AccountInfo accountInfo = getAccount(accountId).getAccountInfo();
    return accountInfo.getBalance();
  }

  private GetAccountResponse getAccount(long accountId) {
    ResponseEntity<GetAccountResponse> response = restTemplate.getForEntity(accountUrl(Long.toString(accountId)),
            GetAccountResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    return response.getBody();
  }


  private void assertCustomerViewUpdated(long customerId, long fromAccountId, Money fromExpectedBalance,
                                         long toAccountId, Money toExpectedBalance, long moneyTransferId) {
    Eventually.eventually(() -> {
      ResponseEntity<CustomerView> response = restTemplate.getForEntity(customerViewUrl(Long.toString(customerId)), CustomerView.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      CustomerView customerView = response.getBody();

      AccountView fromAccount = customerView.getAccounts().get(Long.toString(fromAccountId));
      assertNotNull(fromAccount);
      assertEquals(fromExpectedBalance, fromAccount.getBalance());

      AccountView toAccount = customerView.getAccounts().get(Long.toString(toAccountId));
      assertNotNull(toAccount);
      assertEquals(toExpectedBalance, toAccount.getBalance());
    });
  }

}
