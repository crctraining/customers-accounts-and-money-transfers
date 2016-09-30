package net.chrisrichardson.bankingexample.customerviewservice.web;

import net.chrisrichardson.bankingexample.customerviewservice.backend.CustomerView;
import net.chrisrichardson.bankingexample.customerviewservice.backend.CustomerViewService;
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
    CustomerView customerView = customerViewService.findByCustomerId(id);
    if (customerView == null)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else
      return new ResponseEntity<>(customerView, HttpStatus.OK);
  }


}