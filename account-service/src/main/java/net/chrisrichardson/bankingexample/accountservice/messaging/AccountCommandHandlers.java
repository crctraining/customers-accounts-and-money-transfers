package net.chrisrichardson.bankingexample.accountservice.messaging;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import net.chrisrichardson.bankingexample.accountservice.backend.AccountService;
import net.chrisrichardson.bankingexample.accountservice.backend.InsufficientFundsException;
import net.chrisrichardson.bankingexample.accountservice.common.commands.AccountServiceChannels;
import net.chrisrichardson.bankingexample.accountservice.common.commands.CreditCommand;
import net.chrisrichardson.bankingexample.accountservice.common.commands.DebitCommand;
import net.chrisrichardson.bankingexample.accountservice.common.commands.InsufficientFundsReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class AccountCommandHandlers {

  private Logger logger = LoggerFactory.getLogger(getClass());
  private AccountService accountService;

  public AccountCommandHandlers(AccountService accountService) {
    this.accountService = accountService;
  }

  public CommandHandlers commandHandlers() {
    return SagaCommandHandlersBuilder
            .fromChannel(AccountServiceChannels.accountServiceChannel)
            .onMessage(DebitCommand.class, this::debit)
            .onMessage(CreditCommand.class, this::credit)
            .build();
  }

  public Message debit(CommandMessage<DebitCommand> cm) {
    try {
      accountService.debit(cm.getCommand().getAccountId(), cm.getCommand().getAmount());
      return withSuccess();
    } catch (InsufficientFundsException e) {
      return withFailure(new InsufficientFundsReply());
    }
  }

  public Message credit(CommandMessage<CreditCommand> cm) {
    accountService.credit(cm.getCommand().getAccountId(), cm.getCommand().getAmount());
    return withSuccess();
  }


}
