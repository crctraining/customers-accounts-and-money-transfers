package net.chrisrichardson.bankingexample.moneytransferservice.messaging;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferService;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.CancelMoneyTransferCommand;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.CompleteMoneyTransferCommand;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.MoneyTransferServiceChannels;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class MoneyTransferServiceCommandHandlers {

  private MoneyTransferService moneyTransferService;

  public MoneyTransferServiceCommandHandlers(MoneyTransferService moneyTransferService) {
    this.moneyTransferService = moneyTransferService;
  }

  public CommandHandlers commandHandlers() {
    return SagaCommandHandlersBuilder
            .fromChannel(MoneyTransferServiceChannels.moneyTransferServiceChannel)
            .onMessage(CompleteMoneyTransferCommand.class, this::complete)
            .onMessage(CancelMoneyTransferCommand.class, this::cancel)
            .build();
  }

  private Message complete(CommandMessage<CompleteMoneyTransferCommand> commandMessage) {
    moneyTransferService.completeTransfer(commandMessage.getCommand().getMoneyTransferId());
    return withSuccess();
  }

  private Message cancel(CommandMessage<CancelMoneyTransferCommand> commandMessage) {
    moneyTransferService.cancelTransfer(commandMessage.getCommand().getMoneyTransferId());
    return withSuccess();
  }
}
