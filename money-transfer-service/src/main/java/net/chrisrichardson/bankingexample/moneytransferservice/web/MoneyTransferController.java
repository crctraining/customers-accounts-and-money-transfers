package net.chrisrichardson.bankingexample.moneytransferservice.web;

import io.eventuate.EntityNotFoundException;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransfer;
import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferService;
import net.chrisrichardson.bankingexample.moneytransferservice.common.MoneyTransferInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    return new CreateMoneyTransferResponse(moneyTransferService.createMoneyTransfer(moneyTransferInfo).getEntityId());
  }

  @RequestMapping(value="/{id}", method = RequestMethod.GET)
  public GetMoneyTransferResponse getMoneyTransfer(@PathVariable String id) {
    EntityWithMetadata<MoneyTransfer> x = moneyTransferService.findMoneyTransfer(id);
    return new GetMoneyTransferResponse(x.getEntity().getState(), x.getEntity().getMoneyTransferInfo());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void notFound() {
  }

}