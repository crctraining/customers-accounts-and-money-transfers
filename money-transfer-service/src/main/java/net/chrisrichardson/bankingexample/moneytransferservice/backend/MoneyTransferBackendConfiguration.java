package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;
import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import net.chrisrichardson.bankingexample.moneytransferservice.sagas.AccountServiceProxy;
import net.chrisrichardson.bankingexample.moneytransferservice.sagas.MoneyTransferServiceProxy;
import net.chrisrichardson.bankingexample.moneytransferservice.sagas.TransferMoneySaga;
import net.chrisrichardson.bankingexample.moneytransferservice.sagas.TransferMoneySagaState;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan
@Import({SagaOrchestratorConfiguration.class,SagaParticipantConfiguration.class, TramEventsPublisherConfiguration.class})
public class MoneyTransferBackendConfiguration {

  @Bean
  public MoneyTransferService moneyTransferService(MoneyTransferRepository moneyTransferRepository, SagaManager<TransferMoneySagaState> transferMoneySagaManager) {
    return new MoneyTransferService(moneyTransferRepository, transferMoneySagaManager);
  }


//  @Bean
//  public MoneyTransferEventSubscriber moneyTransferEventSubscriber() {
//    return new MoneyTransferEventSubscriber();
//  }

  @Bean
  public SagaManager<TransferMoneySagaState> transferMoneySagaStateSagaManager(TransferMoneySaga saga) {
    return new SagaManagerImpl<>(saga);
  }

  @Bean
  public TransferMoneySaga transferMoneySaga() {
    return new TransferMoneySaga(new AccountServiceProxy(), new MoneyTransferServiceProxy());
  }

  @Bean
  public ChannelMapping channelMapping() {
    return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
  }

}
