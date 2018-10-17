package net.chrisrichardson.bankingexample.moneytransferservice.messaging;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferBackendConfiguration;
import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MoneyTransferBackendConfiguration.class})
public class MoneyTransferServiceMessagingConfiguration {

  @Bean
  public MoneyTransferServiceCommandHandlers moneyTransferServiceCommandHandlers(MoneyTransferService moneyTransferService) {
    return new MoneyTransferServiceCommandHandlers(moneyTransferService);
  }

  @Bean
  public SagaCommandDispatcher orderCommandHandlersDispatcher(MoneyTransferServiceCommandHandlers moneyTransferServiceCommandHandlers) {
    return new SagaCommandDispatcher("moneyTransferServiceCommands", moneyTransferServiceCommandHandlers.commandHandlers());
  }


}
