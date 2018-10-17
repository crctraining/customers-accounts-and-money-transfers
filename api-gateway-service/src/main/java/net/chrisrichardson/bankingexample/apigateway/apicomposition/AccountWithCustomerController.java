package net.chrisrichardson.bankingexample.apigateway.apicomposition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class AccountWithCustomerController {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  private final AccountWithCustomerService accountWithCustomerService;

  @Autowired
  public AccountWithCustomerController(AccountWithCustomerService accountWithCustomerService) {
    this.accountWithCustomerService = accountWithCustomerService;
  }

  @RequestMapping(value = "/api/accountandcustomer/{accountId}", method = GET)
  public ResponseEntity<AccountWithCustomer> getAccountAndCustomer(@PathVariable long accountId) {
    return accountWithCustomerService.getAccountWithCustomer(accountId).map(awc ->
            new ResponseEntity<>(awc, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
