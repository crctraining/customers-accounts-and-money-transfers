package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountCreditedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountOpenedEvent;
import net.chrisrichardson.bankingexample.commondomain.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

  private AccountRepository accountRepository;
  private DomainEventPublisher domainEventPublisher;

  public AccountService(AccountRepository accountRepository, DomainEventPublisher domainEventPublisher) {
    this.accountRepository = accountRepository;
    this.domainEventPublisher = domainEventPublisher;
  }


  public Account openAccount(AccountInfo accountInfo) {
    Account account = new Account(accountInfo);
    accountRepository.save(account);
    publishAccountOpenedEvent(account);
    return account;
  }

  private void publishAccountOpenedEvent(Account account) {
    domainEventPublisher.publish(Account.class, account.getId(), Collections.singletonList(new AccountOpenedEvent(account.getAccountInfo())));
  }

  public Optional<Account> findAccount(long id) {
    return accountRepository.findById(id);
  }

  public void noteCustomerValidated(long accountId) {
    Account account = accountRepository.findById(accountId).get();
    account.noteCustomerValidated();
  }

  public void noteCustomerValidationFailed(long accountId) {
    Account account = accountRepository.findById(accountId).get();
    account.noteCustomerValidationFailed();

  }

  public void debit(long accountId, Money amount) {
    Account account = accountRepository.findById(accountId).get();
    account.debit(amount);
    publishAccountDebitedEvent(amount, account);

  }

  private void publishAccountDebitedEvent(Money amount, Account account) {
    domainEventPublisher.publish(Account.class, account.getId(), Collections.singletonList(new AccountDebitedEvent(account.getCustomerId(), amount, account.getBalance(), "unknown")));
  }

  public void credit(long accountId, Money amount) {
    Account account = accountRepository.findById(accountId).get();
    account.credit(amount);
    publishAccountCreditedEvent(amount, account);
  }

  private void publishAccountCreditedEvent(Money amount, Account account) {
    domainEventPublisher.publish(Account.class, account.getId(), Collections.singletonList(new AccountCreditedEvent(account.getCustomerId(), amount, account.getBalance(), "unknown")));
  }
}
