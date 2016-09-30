package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferCreatedEvent;
import net.chrisrichardson.bankingexample.moneytransferservice.common.events.MoneyTransferDebitRecordedEvent;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id="bankingExampleAccountEventSubscriber")
public class AccountEventSubscriber {

  @EventHandlerMethod
  public CompletableFuture<?> performDebit(EventHandlerContext<MoneyTransferCreatedEvent> ctx) {
    MoneyTransferInfo moneyTransferInfo = ctx.getEvent().getMoneyTransferInfo();
    return ctx.update(Account.class, moneyTransferInfo.getFromAccountId(),
            new DebitAccountCommand(moneyTransferInfo.getAmount(), ctx.getEntityId()));
  }

  @EventHandlerMethod
  public CompletableFuture<?> performCredit(EventHandlerContext<MoneyTransferDebitRecordedEvent> ctx) {
    MoneyTransferInfo moneyTransferInfo = ctx.getEvent().getMoneyTransferInfo();
    return ctx.update(Account.class, moneyTransferInfo.getToAccountId(),
            new CreditAccountCommand(moneyTransferInfo.getAmount(), ctx.getEntityId()));
  }
}
