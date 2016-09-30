package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.eventuate.testutil.AsyncUtil.await;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MoneyTransferIntegrationTestConfiguration.class)
@IntegrationTest
public class MoneyTransferServiceIntegrationTest {

  @Autowired
  private MoneyTransferService moneyTransferService;

  @Test
  public void shouldSaveAndLoadMoneyTransfer() {

    MoneyTransferInfo moneyTransferInfo = MoneyTransferMother.makeMoneyTransfer();

    EntityWithIdAndVersion<MoneyTransfer> savedMoneyTransfer = moneyTransferService.createMoneyTransfer(moneyTransferInfo);

    EntityWithMetadata<MoneyTransfer> loadedMoneyTransfer = moneyTransferService.findMoneyTransfer(savedMoneyTransfer.getEntityId());

    assertNotNull(loadedMoneyTransfer);

    assertEquals(moneyTransferInfo, loadedMoneyTransfer.getEntity().getMoneyTransferInfo());
  }
}