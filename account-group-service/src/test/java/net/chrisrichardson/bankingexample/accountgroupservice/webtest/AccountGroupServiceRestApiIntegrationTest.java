package net.chrisrichardson.bankingexample.accountgroupservice.webtest;

import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import io.eventuate.testutil.Eventually;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.AccountGroupState;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.AccountGroupInfo;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.CreateAccountGroupResponse;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.GetAccountGroupResponse;
import net.chrisrichardson.bankingexample.accountgroupservice.web.AccountGroupServiceWebConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AccountGroupServiceRestApiIntegrationTest.AccountGroupServiceRestApiIntegrationTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountGroupServiceRestApiIntegrationTest {


  @Value("${local.server.port}")
  private int port;

  @Autowired
  private RestTemplate restTemplate;

  public String baseUrl(String path) {
    return "http://localhost:" + port + "/api" + path;
  }

  @Test
  public void shouldSaveAndLoadAccountGroup() {

    AccountGroupInfo accountInfo = new AccountGroupInfo(null, "my-account-group");

    CreateAccountGroupResponse accountResponse = createAccountGroup(accountInfo);

    GetAccountGroupResponse loadedAccountInfo = getAccountGroup(accountResponse.getAccountGroupId());

    assertEquals(accountInfo.getName(), loadedAccountInfo.getName());
    assertEquals(AccountGroupState.CREATED.name(), loadedAccountInfo.getState());
  }

  @Test
  public void shouldCreateChildGroup() {

    AccountGroupInfo parentAccountInfo = new AccountGroupInfo(null, "my-account-parent");
    CreateAccountGroupResponse parentAccountResponse = createAccountGroup(parentAccountInfo);


    AccountGroupInfo accountInfo = new AccountGroupInfo(parentAccountResponse.getAccountGroupId(), "my-account-child");

    CreateAccountGroupResponse accountResponse = createAccountGroup(accountInfo);


    assertEventualAccountGroupState(AccountGroupState.CREATED, accountResponse.getAccountGroupId());
  }

  @Test
  public void shouldFailToCreateChildGroup() {

    AccountGroupInfo accountInfo = new AccountGroupInfo("non-existent-parent", "my-account-child");

    CreateAccountGroupResponse accountResponse = createAccountGroup(accountInfo);

    assertEventualAccountGroupState(AccountGroupState.CREATION_FAILED, accountResponse.getAccountGroupId());
  }


  private void assertEventualAccountGroupState(AccountGroupState expectedState, String accountGroupId) {
    Eventually.eventually(() -> {
      GetAccountGroupResponse loadedAccountInfo = getAccountGroup(accountGroupId);
      assertEquals(expectedState.name(), loadedAccountInfo.getState());

    });
  }

  private CreateAccountGroupResponse createAccountGroup(AccountGroupInfo accountInfo) {
    ResponseEntity<CreateAccountGroupResponse> createResponse = restTemplate.postForEntity(baseUrl("/accountgroups"), accountInfo, CreateAccountGroupResponse.class);
    assertEquals(HttpStatus.OK, createResponse.getStatusCode());
    return createResponse.getBody();
  }

  private GetAccountGroupResponse getAccountGroup(String accountGroupId) {
    ResponseEntity<GetAccountGroupResponse> getResponse = restTemplate.getForEntity(baseUrl("/accountgroups/" + accountGroupId),
            GetAccountGroupResponse.class);

    assertEquals(HttpStatus.OK, getResponse.getStatusCode());

    GetAccountGroupResponse loadedAccountInfo = getResponse.getBody();

    assertNotNull(loadedAccountInfo);
    return loadedAccountInfo;
  }

  @Configuration
  @Import({AccountGroupServiceWebConfiguration.class,EmbeddedTestAggregateStoreConfiguration.class})
  @EnableAutoConfiguration
  public static class AccountGroupServiceRestApiIntegrationTestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }

  }
}
