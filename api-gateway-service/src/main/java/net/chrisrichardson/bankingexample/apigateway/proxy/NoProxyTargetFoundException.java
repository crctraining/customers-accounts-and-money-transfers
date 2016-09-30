package net.chrisrichardson.bankingexample.apigateway.proxy;

public class NoProxyTargetFoundException extends RuntimeException {
  public NoProxyTargetFoundException(String m) {
    super(m);
  }
}
