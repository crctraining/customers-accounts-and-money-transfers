package net.chrisrichardson.bankingexample.moneytransferservice.sagas;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import net.chrisrichardson.bankingexample.accountservice.common.commands.AccountServiceChannels;
import net.chrisrichardson.bankingexample.accountservice.common.commands.CreditCommand;
import net.chrisrichardson.bankingexample.accountservice.common.commands.DebitCommand;

public class AccountServiceProxy {

  public final CommandEndpoint<DebitCommand> debit= CommandEndpointBuilder
          .forCommand(DebitCommand.class)
          .withChannel(AccountServiceChannels.accountServiceChannel)
          .withReply(Success.class)
          .build();

  public final CommandEndpoint<CreditCommand> credit = CommandEndpointBuilder
          .forCommand(CreditCommand.class)
          .withChannel(AccountServiceChannels.accountServiceChannel)
          .withReply(Success.class)
          .build();

}
