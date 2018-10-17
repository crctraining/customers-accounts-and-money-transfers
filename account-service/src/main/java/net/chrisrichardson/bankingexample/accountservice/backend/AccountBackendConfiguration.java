package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan
@Import({TramEventsPublisherConfiguration.class, SagaParticipantConfiguration.class})
public class AccountBackendConfiguration {

  @Bean
  public AccountService accountService(AccountRepository accountRepository, DomainEventPublisher domainEventPublisher) {
    return new AccountService(accountRepository, domainEventPublisher);
  }

  @Bean
  public ChannelMapping channelMapping() {
    return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
  }

}
