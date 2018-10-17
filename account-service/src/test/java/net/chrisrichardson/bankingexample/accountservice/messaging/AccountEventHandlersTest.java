package net.chrisrichardson.bankingexample.accountservice.messaging;

import net.chrisrichardson.bankingexample.accountservice.backend.AccountService;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerValidatedEvent;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerValidationFailedEvent;
import org.junit.Before;
import org.junit.Test;

import static io.eventuate.tram.testing.DomainEventHandlerUnitTestSupport.given;
import static net.chrisrichardson.bankingexample.accountservice.backend.AccountMother.accountId;
import static net.chrisrichardson.bankingexample.accountservice.backend.AccountMother.customerId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountEventHandlersTest {

  private AccountEventHandlers accountEventHandlers;
  private AccountService accountService;

  @Before
  public void setUp() {
    accountService = mock(AccountService.class);
    accountEventHandlers = new AccountEventHandlers(accountService);
  }

  @Test
  public void shouldHandleCustomerValidatedEvent() {
    given().
      eventHandlers(accountEventHandlers.domainEventHandlers()).
    when().
      aggregate("net.chrisrichardson.bankingexample.customerservice.backend.Customer", customerId).
      publishes(new CustomerValidatedEvent(accountId)).
    then().
      verify(() -> {
        verify(accountService).noteCustomerValidated(accountId);
      })
    ;

  }
  @Test
  public void shouldHandleCustomerValidationFailedEvent() {
    given().
      eventHandlers(accountEventHandlers.domainEventHandlers()).
    when().
      aggregate("net.chrisrichardson.bankingexample.customerservice.backend.Customer", customerId).
      publishes(new CustomerValidationFailedEvent(accountId)).
    then().
      verify(() -> {
        verify(accountService).noteCustomerValidationFailed(accountId);
      })
    ;

  }

}