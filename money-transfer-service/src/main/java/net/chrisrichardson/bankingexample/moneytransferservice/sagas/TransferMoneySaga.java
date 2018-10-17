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
    this.sagaDefinition =
             step()
              .withCompensation(moneyTransferService.cancel, TransferMoneySagaState::makeCancelMoneyTransferCommand)
            .step()
              .invokeParticipant(accountingService.debit, TransferMoneySagaState::makeDebitAccountCommand)
              .withCompensation(accountingService.credit, TransferMoneySagaState::makeReverseDebitCommand)
            .step()
              .invokeParticipant(accountingService.credit, TransferMoneySagaState::makeCreditAccountCommand)
            .step()
              .invokeParticipant(moneyTransferService.complete, TransferMoneySagaState::makeCompleteMoneyTransferCommand)
            .build();
  }


  @Override
  public SagaDefinition<TransferMoneySagaState> getSagaDefinition() {
    return sagaDefinition;
  }


}
