package net.chrisrichardson.bankingexample.apigateway.apicomposition;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomerServiceProxy {

  private RestTemplate restTemplate;

  public CustomerServiceProxy(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  private ConcurrentHashMap<Long, CustomerInfo> cachedCustomerInfo = new ConcurrentHashMap<>();

  @HystrixCommand(fallbackMethod = "getCachedCustomerInfo", commandProperties = @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000"))
  public CustomerInfo getCustomer(long customerId) {
    ResponseEntity<CustomerInfo> response = restTemplate.getForEntity("http://CUSTOMER-SERVICE/api/customers/" + customerId, CustomerInfo.class);
    if (response.getStatusCode() != HttpStatus.OK)
      throw new RuntimeException("Got bad status code: " + response.getStatusCode());
    CustomerInfo ci = response.getBody();
    cachedCustomerInfo.put(customerId, ci);
    return ci;
  }

  public CustomerInfo getCachedCustomerInfo(long customerId) {
    return cachedCustomerInfo.get(customerId);
  }

  }
