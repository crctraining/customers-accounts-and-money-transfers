package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.EventSubscriber;

@EventSubscriber(id="bankingExampleMoneyTransfer")
public class MoneyTransferEventSubscriber {

  /*
  @EventHandlerMethod
  public CompletableFuture<?> handleAccountDebitedEvent(EventHandlerContext<AccountDebitedEvent> ctx) {
    AccountDebitedEvent event = ctx.getEvent();
    if (event.getTransactionId() == null)
      return  CompletableFuture.completedFuture(null);

    return ctx.update(MoneyTransfer.class, event.getTransactionId(), new RecordDebitCommand(ctx.getEntityId()));
  }

  @EventHandlerMethod
  public CompletableFuture<?> handleAccountDebitFailedEvent(EventHandlerContext<AccountDebitFailedDueToInsufficientFundsEvent> ctx) {
    String transactionId = ctx.getEvent().getTransactionId();
    if (transactionId == null)
      return  CompletableFuture.completedFuture(null);

    return ctx.update(MoneyTransfer.class, transactionId, new RecordDebitFailedDueToInsufficientFundsCommand(ctx.getEntityId()));
  }

  @EventHandlerMethod
  public CompletableFuture<?> handleAccountCredited(EventHandlerContext<AccountCreditedEvent> ctx) {
    AccountCreditedEvent event = ctx.getEvent();
    if (event.getTransactionId() == null)
      return  CompletableFuture.completedFuture(null);

    return ctx.update(MoneyTransfer.class, event.getTransactionId(), new RecordCreditCommand(ctx.getEntityId()));
  }
  */

}
