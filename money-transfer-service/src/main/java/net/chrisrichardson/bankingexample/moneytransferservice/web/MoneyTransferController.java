package net.chrisrichardson.bankingexample.moneytransferservice.web;

import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferService;
import net.chrisrichardson.bankingexample.moneytransferservice.common.CreateMoneyTransferResponse;
import net.chrisrichardson.bankingexample.moneytransferservice.common.GetMoneyTransferResponse;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/api/moneytransfers")
public class MoneyTransferController {

  private MoneyTransferService moneyTransferService;

  @Autowired
  public MoneyTransferController(MoneyTransferService moneyTransferService) {
    this.moneyTransferService = moneyTransferService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public CreateMoneyTransferResponse createMoneyTransfer(@Validated @RequestBody MoneyTransferInfo moneyTransferInfo) {
    assertNotNull(moneyTransferInfo);
    return new CreateMoneyTransferResponse(moneyTransferService.createMoneyTransfer(moneyTransferInfo).getId());
  }

  @RequestMapping(value="/{id}", method = RequestMethod.GET)
  public ResponseEntity<GetMoneyTransferResponse> getMoneyTransfer(@PathVariable long id) {
    return moneyTransferService.findMoneyTransfer(id)
            .map(x -> new ResponseEntity<>(new GetMoneyTransferResponse(x.getState(), x.getMoneyTransferInfo()), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}