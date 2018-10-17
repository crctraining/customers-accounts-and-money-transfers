package net.chrisrichardson.bankingexample.accountservice.web;

import net.chrisrichardson.bankingexample.accountservice.backend.AccountService;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.accountservice.common.CreateAccountResponse;
import net.chrisrichardson.bankingexample.accountservice.common.GetAccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    return new CreateAccountResponse(accountService.openAccount(accountInfo).getId());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<GetAccountResponse> getAccount(@PathVariable long id) {
    return accountService.findAccount(id)
            .map(c -> new ResponseEntity<>(new GetAccountResponse(c.getAccountInfo(), c.getState()), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }



}