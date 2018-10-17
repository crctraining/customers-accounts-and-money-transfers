package net.chrisrichardson.bankingexample.accountgroupservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.AccountGroupInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AccountGroupServiceIntegrationTest.AccountGroupServiceIntegrationTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AccountGroupServiceIntegrationTest {

  private String accountGroupName = "my-account-group";
  ;

  @Configuration
  @Import({AccountGroupBackendConfiguration.class, EmbeddedTestAggregateStoreConfiguration.class})
  @EnableAutoConfiguration
  public static class AccountGroupServiceIntegrationTestConfiguration {
  }

  @Autowired
  private AccountGroupService accountGroupService;

  @Test
  public void shouldCreateAccountGroupAndAddAccount() {
    EntityWithIdAndVersion<AccountGroup> accountGroup = accountGroupService.createAccountGroup(new AccountGroupInfo(null, accountGroupName));

    EntityWithMetadata<AccountGroup> accountGroup2 = accountGroupService.findAccountGroup(accountGroup.getEntityId());

    assertEquals(accountGroupName, accountGroup2.getEntity().getName());

    accountGroupService.addAccount(accountGroup.getEntityId(), "my-accountGroup");

  }

  @Test
  public void shouldVerifyParentGroupExists_exists() {
    EntityWithIdAndVersion<AccountGroup> accountGroup = accountGroupService.createAccountGroup(new AccountGroupInfo(null, accountGroupName));
    EntityWithIdAndVersion<AccountGroup> parentGroup = accountGroupService.verifyParentGroupExists(accountGroup.getEntityId(), "child-account-group-1");
    assertEquals(AccountGroupState.CREATED, parentGroup.getAggregate().getState());
  }

  @Test
  public void shouldVerifyParentGroupExists_nonexistent() {
    EntityWithIdAndVersion<AccountGroup> parentGroup = accountGroupService.verifyParentGroupExists("non-existent-account-group-" + System.currentTimeMillis(), "child-account-group-1");
    assertEquals(AccountGroupState.MISSING_PARENT, parentGroup.getAggregate().getState());
  }
}