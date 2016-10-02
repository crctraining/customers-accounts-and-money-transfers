package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import io.eventuate.sync.AggregateRepository;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferService {

  private final AggregateRepository<MoneyTransfer, MoneyTransferCommand> moneyTransferRepository;

  public MoneyTransferService(AggregateRepository<MoneyTransfer, MoneyTransferCommand> moneyTransferRepository) {
    this.moneyTransferRepository = moneyTransferRepository;
  }

  public EntityWithIdAndVersion<MoneyTransfer> createMoneyTransfer(MoneyTransferInfo moneyTransferInfo) {

    // TODO verify that the from/toAccounts exist

    return moneyTransferRepository.save(new CreateMoneyTransferCommand(moneyTransferInfo));
  }

  public EntityWithMetadata<MoneyTransfer> findMoneyTransfer(String id) {
    return moneyTransferRepository.find(id);
  }
}
