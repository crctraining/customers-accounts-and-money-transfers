package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.tram.sagas.orchestration.SagaManager;
import net.chrisrichardson.bankingexample.moneytransferservice.sagas.TransferMoneySagaState;
import org.junit.Before;
import org.junit.Test;

import static net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferMother.moneyTransferInfo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoneyTransferServiceTest {

  private SagaManager<TransferMoneySagaState> transferMoneySagaManager;
  private MoneyTransferService moneyTransferService;
  private MoneyTransferRepository moneyTransferRepository;

  @Before
  public void setUp() {
    moneyTransferRepository = mock(MoneyTransferRepository.class);
    transferMoneySagaManager = mock(SagaManager.class);

    moneyTransferService = new MoneyTransferService(moneyTransferRepository, transferMoneySagaManager);
  }

  @Test
  public void shouldCreateMoneyTransfer() {

    when(moneyTransferRepository.save(any(MoneyTransfer.class))).thenAnswer(invocation -> {
      ((MoneyTransfer)invocation.getArguments()[0]).setId(MoneyTransferMother.moneyTransferId);
      return null;
    });

    MoneyTransfer moneyTransfer = moneyTransferService.createMoneyTransfer(moneyTransferInfo);

    verify(moneyTransferRepository).save(moneyTransfer);
    verify(transferMoneySagaManager).create(any(TransferMoneySagaState.class));
  }
}