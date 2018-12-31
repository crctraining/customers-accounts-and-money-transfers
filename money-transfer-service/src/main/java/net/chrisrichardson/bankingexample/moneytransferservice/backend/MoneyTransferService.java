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
    throw new RuntimeException("not yet implemented");
  }

  private void createTransferMoneySaga(Long moneyTransferId, MoneyTransferInfo moneyTransferInfo) {
    TransferMoneySagaState data = new TransferMoneySagaState(moneyTransferId, moneyTransferInfo);
    transferMoneySagaManager.create(data);
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
