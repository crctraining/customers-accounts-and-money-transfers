package net.chrisrichardson.bankingexample.moneytransferservice.webtest;

import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferMother;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import net.chrisrichardson.bankingexample.moneytransferservice.web.CreateMoneyTransferResponse;
import net.chrisrichardson.bankingexample.moneytransferservice.web.GetMoneyTransferResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MoneyTransferServiceRestApiIntegrationTestConfiguration.class)
@WebIntegrationTest(randomPort = true)
public class MoneyTransferServiceRestApiIntegrationTest {

  @Value("${local.server.port}")
  private int port;

  @Autowired
  private RestTemplate restTemplate;

  public String baseUrl(String path) {
    return "http://localhost:" + port + "/api" + path;
  }

  @Test
  public void shouldSaveAndLoadMoneyTransfer() {

    MoneyTransferInfo moneyTransferInfo = MoneyTransferMother.makeMoneyTransfer();

   CreateMoneyTransferResponse moneyTransferResponse = restTemplate.postForEntity(baseUrl("/moneytransfers"),
            moneyTransferInfo,
            CreateMoneyTransferResponse.class).getBody();


    assertNotNull(moneyTransferResponse.getId());

    GetMoneyTransferResponse loadedMoneyTransferInfo = restTemplate.getForEntity(baseUrl("/moneytransfers/" + moneyTransferResponse.getId()),
            GetMoneyTransferResponse.class).getBody();

    assertNotNull(loadedMoneyTransferInfo);
    assertNotNull(loadedMoneyTransferInfo.getState());
    assertEquals(moneyTransferInfo, loadedMoneyTransferInfo.getMoneyTransferInfo());
  }
}