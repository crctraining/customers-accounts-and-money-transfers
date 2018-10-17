package net.chrisrichardson.bankingexample.moneytransferservice.sagas;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.CancelMoneyTransferCommand;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.CompleteMoneyTransferCommand;
import net.chrisrichardson.bankingexample.moneytransferservice.common.commands.MoneyTransferServiceChannels;

public class MoneyTransferServiceProxy {

  public final CommandEndpoint<CancelMoneyTransferCommand> cancel = CommandEndpointBuilder
          .forCommand(CancelMoneyTransferCommand.class)
          .withChannel(MoneyTransferServiceChannels.moneyTransferServiceChannel)
          .withReply(Success.class)
          .build();

  public final CommandEndpoint<CompleteMoneyTransferCommand> complete = CommandEndpointBuilder
          .forCommand(CompleteMoneyTransferCommand.class)
          .withChannel(MoneyTransferServiceChannels.moneyTransferServiceChannel)
          .withReply(Success.class)
          .build();

}
