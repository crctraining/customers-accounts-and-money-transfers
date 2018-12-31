package net.chrisrichardson.bankingexample.moneytransferservice.sagas;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferMoneySaga implements SimpleSaga<TransferMoneySagaState> {


  private Logger logger = LoggerFactory.getLogger(getClass());

  private SagaDefinition<TransferMoneySagaState> sagaDefinition;

  public TransferMoneySaga(AccountServiceProxy accountingService, MoneyTransferServiceProxy moneyTransferService) {
    initializeSagaDefinition(accountingService, moneyTransferService);
  }

  private void initializeSagaDefinition(AccountServiceProxy accountingService, MoneyTransferServiceProxy moneyTransferService) {
    throw new RuntimeException("not yet implemented");
  }


  @Override
  public SagaDefinition<TransferMoneySagaState> getSagaDefinition() {
    return sagaDefinition;
  }


}
