package net.chrisrichardson.bankingexample.customerservice.webtest;

import net.chrisrichardson.bankingexample.customerservice.backend.CustomerMother;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import net.chrisrichardson.bankingexample.customerservice.common.CreateCustomerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerServiceRestApiIntegrationTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceRestApiIntegrationTest {

  @Value("${local.server.port}")
  private int port;

  @Autowired
  private RestTemplate restTemplate;

  public String baseUrl(String path) {
    return "http://localhost:" + port + "/api" + path;
  }

  @Test
  public void shouldSaveAndLoadCustomer() {

    CustomerInfo customerInfo = CustomerMother.makeCustomer();


    CreateCustomerResponse customerResponse = restTemplate.postForEntity(baseUrl("/customers"),
            customerInfo,
            CreateCustomerResponse.class).getBody();


    assertNotNull(customerResponse.getId());

    CustomerInfo loadedCustomerInfo = restTemplate.getForEntity(baseUrl("/customers/" + customerResponse.getId()),
            CustomerInfo.class).getBody();

    assertNotNull(loadedCustomerInfo);

    assertEquals(customerInfo, loadedCustomerInfo);
  }
}