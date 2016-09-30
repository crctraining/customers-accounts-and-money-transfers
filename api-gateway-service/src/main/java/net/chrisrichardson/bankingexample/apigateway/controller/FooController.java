package net.chrisrichardson.bankingexample.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FooController {


  @RequestMapping(value = "/api/orderhistory/{id}", method = GET)
  public ResponseEntity<String> proxyRequest(@PathVariable String id) throws NoSuchRequestHandlingMethodException, IOException, URISyntaxException {
    return new ResponseEntity<>("hello: " + id, HttpStatus.OK);
  }
}
