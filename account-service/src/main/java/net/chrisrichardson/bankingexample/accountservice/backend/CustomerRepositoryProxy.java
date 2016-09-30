package net.chrisrichardson.bankingexample.accountservice.backend;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CustomerRepositoryProxy implements CustomerRepository {

  private RestTemplate restTemplate;

  @Value("${customer.service.url}")
  private String customerServiceUrl;

  public CustomerRepositoryProxy(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  @HystrixCommand(commandProperties = @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000"))
  public CustomerInfo findCustomerById(String id) {
    System.out.println("Using customerServiceUrl=" + customerServiceUrl);
    ResponseEntity<CustomerInfo> responseEntity = null;
    try {
      responseEntity = restTemplate.getForEntity(customerServiceUrl + "/" + id, CustomerInfo.class);
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND)
        throw new CustomerNotFoundException("Cannot find customer: " + id);
      else
        throw e;
    }
    if (responseEntity.getStatusCode() == HttpStatus.OK)
      return responseEntity.getBody();
    else
      throw new RuntimeException("Bad response: " + responseEntity.getStatusCode());
  }
}
