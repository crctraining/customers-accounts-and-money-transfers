package net.chrisrichardson.bankingexample.accountgroupservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.commands.AccountGroupCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountGroupBackendConfiguration {


  @Bean
  public AccountGroupService accountGroupService(AggregateRepository<AccountGroup, AccountGroupCommand> accountGroupRepository) {
    return new AccountGroupService(accountGroupRepository);
  }

  @Bean
  public AggregateRepository<AccountGroup, AccountGroupCommand> accountGroupRepository(EventuateAggregateStore aggregateStore) {
    return new AggregateRepository<AccountGroup, AccountGroupCommand>(AccountGroup.class, aggregateStore);
  }
}
