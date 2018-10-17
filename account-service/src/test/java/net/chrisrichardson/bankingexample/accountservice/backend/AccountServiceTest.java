package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountOpenedEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static net.chrisrichardson.bankingexample.accountservice.backend.AccountMother.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {


  private AccountService accountService;
  private AccountRepository accountRepository;
  private DomainEventPublisher domainEventPublisher;

  @Before
  public void setUp() {
    accountRepository = mock(AccountRepository.class);
    domainEventPublisher = mock(DomainEventPublisher.class);

    accountService = new AccountService(accountRepository, domainEventPublisher);
  }

  @Test
  public void shouldOpenAccount() {

    when(accountRepository.save(any(Account.class))).then(invocation -> {
      ((Account) invocation.getArguments()[0]).setId(accountId);
      return null;
    });

    AccountInfo accountInfo = new AccountInfo(customerId, "Checking", initialBalance);
    Account account = accountService.openAccount(accountInfo);

    verify(accountRepository).save(account);

    verify(domainEventPublisher).publish(Account.class, accountId, Collections.singletonList(new AccountOpenedEvent(accountInfo)));
  }
}