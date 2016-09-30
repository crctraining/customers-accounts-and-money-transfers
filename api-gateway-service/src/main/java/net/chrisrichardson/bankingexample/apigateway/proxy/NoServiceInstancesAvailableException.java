package net.chrisrichardson.bankingexample.apigateway.proxy;

import java.net.URI;

public class NoServiceInstancesAvailableException extends RuntimeException {
  public NoServiceInstancesAvailableException(URI url) {
    super("No instances available: " + url);
  }
}
