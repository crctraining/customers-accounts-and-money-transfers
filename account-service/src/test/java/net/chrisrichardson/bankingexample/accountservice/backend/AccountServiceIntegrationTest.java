package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AccountServiceIntegrationTestConfiguration.class)
@IntegrationTest
public class AccountServiceIntegrationTest {


  @Autowired
  private AccountService accountService;

  @Test
  public void shouldSaveAndLoadAccount() {

    AccountInfo accountInfo = AccountMother.makeAccount("customer-123");

    // Account savedAccount = accountService.createAccount(accountInfo);
    EntityWithIdAndVersion<Account> savedAccount = accountService.createAccount(accountInfo);

    //    Account loadedAccount = accountService.findAccount(savedAccount.getId());
    Account loadedAccount = accountService.findAccount(savedAccount.getEntityId());

    assertNotNull(loadedAccount);

    assertEquals(accountInfo, loadedAccount.getAccountInfo());
  }
}