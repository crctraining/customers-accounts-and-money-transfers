package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.tram.sagas.orchestration.SagaManager;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.sagas.TransferMoneySagaState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MoneyTransferService {

  private final MoneyTransferRepository moneyTransferRepository;
  private final SagaManager<TransferMoneySagaState> transferMoneySagaManager;

  public MoneyTransferService(MoneyTransferRepository moneyTransferRepository, SagaManager<TransferMoneySagaState> transferMoneySagaManager) {
    this.moneyTransferRepository = moneyTransferRepository;
    this.transferMoneySagaManager = transferMoneySagaManager;
  }

  public MoneyTransfer createMoneyTransfer(MoneyTransferInfo moneyTransferInfo) {

    MoneyTransfer mt = new MoneyTransfer(moneyTransferInfo);
    moneyTransferRepository.save(mt);

    createTransferMoneySaga(mt.getId(), moneyTransferInfo);

    return mt;
  }

  private void createTransferMoneySaga(Long moneyTransferId, MoneyTransferInfo moneyTransferInfo) {
    throw new RuntimeException("not yet implemented");
  }

  public Optional<MoneyTransfer> findMoneyTransfer(long id) {
    return moneyTransferRepository.findById(id);
  }

  public void completeTransfer(long moneyTransferId) {
    moneyTransferRepository.findById(moneyTransferId).get().complete();
  }

  public void cancelTransfer(long moneyTransferId) {
    moneyTransferRepository.findById(moneyTransferId).get().cancel();
  }
}
