package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MoneyTransferIntegrationTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MoneyTransferServiceIntegrationTest {

  @Autowired
  private MoneyTransferService moneyTransferService;

  @Test
  public void shouldSaveAndLoadMoneyTransfer() {

    MoneyTransferInfo moneyTransferInfo = MoneyTransferMother.makeMoneyTransfer();

    MoneyTransfer savedMoneyTransfer = moneyTransferService.createMoneyTransfer(moneyTransferInfo);

    Optional<MoneyTransfer> loadedMoneyTransfer = moneyTransferService.findMoneyTransfer(savedMoneyTransfer.getId());

    assertTrue(loadedMoneyTransfer.isPresent());

    assertEquals(moneyTransferInfo, loadedMoneyTransfer.get().getMoneyTransferInfo());
  }
}