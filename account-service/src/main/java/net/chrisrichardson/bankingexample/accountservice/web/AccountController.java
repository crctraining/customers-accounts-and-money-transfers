package net.chrisrichardson.bankingexample.accountservice.web;

import io.eventuate.EntityNotFoundException;
import net.chrisrichardson.bankingexample.accountservice.backend.AccountService;
import net.chrisrichardson.bankingexample.accountservice.backend.CustomerNotFoundException;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public CreateAccountResponse createAccount(@Validated @RequestBody AccountInfo accountInfo) {
    assertNotNull(accountInfo);
    return new CreateAccountResponse(accountService.createAccount(accountInfo).getEntityId());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public AccountInfo getAccount(@PathVariable String id) {
    return accountService.findAccount(id).getAccountInfo();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void notFound() {
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid customerId")
  public void badCustomerId() {
  }

//  @ExceptionHandler(HystrixRuntimeException.class)
//  public ResponseEntity<String> badCustomerIdNested(HystrixRuntimeException e) {
//    if (e.getCause() instanceof CustomerNotFoundException) {
//      return new ResponseEntity<>("Invalid customerId", HttpStatus.BAD_REQUEST);
//    } else {
//      logger.error("Hystrix related exception", e);
//      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//  }

}
