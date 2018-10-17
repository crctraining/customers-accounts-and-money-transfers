package net.chrisrichardson.bankingexample.customerviewservice.web;

import net.chrisrichardson.bankingexample.customerviewservice.backend.CustomerViewService;
import net.chrisrichardson.bankingexample.customerviewservice.common.CustomerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customerview")
public class CustomerViewController {

  @Autowired
  private CustomerViewService customerViewService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<CustomerView> getCustomer(@PathVariable String id) {
    return customerViewService.findByCustomerId(id)
            .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }


}