package net.chrisrichardson.bankingexample.accountservice.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class MockPassengerRepositoryConfiguration {

  @Bean
  public CustomerRepository customerRepository() {
    CustomerRepository customerRepository = mock(CustomerRepository.class);
    when(customerRepository.findCustomerById(anyString())).thenReturn(new CustomerInfo());
    return customerRepository;
  }

}
