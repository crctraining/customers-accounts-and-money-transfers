package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountCreditedEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitFailedDueToInsufficientFundsEvent;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountDebitedEvent;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id="bankingExampleMoneyTransfer")
public class MoneyTransferEventSubscriber {

  @EventHandlerMethod
  public CompletableFuture<?> handleAccountDebitedEvent(EventHandlerContext<AccountDebitedEvent> ctx) {
    AccountDebitedEvent event = ctx.getEvent();
    if (event.getTransactionId() == null)
      return  CompletableFuture.completedFuture(null);

    return ctx.update(MoneyTransfer.class, event.getTransactionId(), new RecordDebitCommand(ctx.getEntityId()));
  }

  @EventHandlerMethod
  public CompletableFuture<?> handleAccountDebitFailedEvent(EventHandlerContext<AccountDebitFailedDueToInsufficientFundsEvent> ctx) {
    throw new RuntimeException("not yet implemented");
  }

  @EventHandlerMethod
  public CompletableFuture<?> handleAccountCredited(EventHandlerContext<AccountCreditedEvent> ctx) {
    throw new RuntimeException("not yet implemented");
  }
}
