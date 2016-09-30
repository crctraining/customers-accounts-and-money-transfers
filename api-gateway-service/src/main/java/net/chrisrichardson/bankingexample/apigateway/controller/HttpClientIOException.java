package net.chrisrichardson.bankingexample.apigateway.controller;

import java.io.IOException;

public class HttpClientIOException extends RuntimeException {
  public HttpClientIOException(IOException e) {
    super(e);
  }
}
