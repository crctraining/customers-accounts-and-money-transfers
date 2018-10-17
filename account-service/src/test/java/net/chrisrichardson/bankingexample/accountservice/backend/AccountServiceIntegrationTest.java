package net.chrisrichardson.bankingexample.accountservice.backend;

import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AccountServiceIntegrationTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AccountServiceIntegrationTest {


  @Autowired
  private AccountService accountService;

  @Test
  public void shouldSaveAndLoadAccount() {

    AccountInfo accountInfo = AccountMother.makeAccount();

    // Account savedAccount = accountService.openAccount(accountInfo);
    Account savedAccount = accountService.openAccount(accountInfo);

    //    Account loadedAccount = accountService.findAccount(savedAccount.getId());
    Optional<Account> loadedAccount = accountService.findAccount(savedAccount.getId());

    assertTrue(loadedAccount.isPresent());

    assertEquals(accountInfo, loadedAccount.get().getAccountInfo());
  }

}