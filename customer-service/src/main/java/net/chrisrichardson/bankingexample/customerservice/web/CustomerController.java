package net.chrisrichardson.bankingexample.customerservice.web;

import net.chrisrichardson.bankingexample.customerservice.backend.CustomerService;
import net.chrisrichardson.bankingexample.customerservice.common.CreateCustomerResponse;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public CreateCustomerResponse createCustomer(@Validated @RequestBody CustomerInfo customerInfo) {
    assertNotNull(customerInfo);
    return new CreateCustomerResponse(customerService.createCustomer(customerInfo).getId());
  }


  @RequestMapping(value="/{id}", method = RequestMethod.GET)
  public ResponseEntity<CustomerInfo> getCustomer(@PathVariable long id) {
    return customerService.findCustomer(id)
            .map(c -> new ResponseEntity<>(c.getCustomerInfo(), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }

}