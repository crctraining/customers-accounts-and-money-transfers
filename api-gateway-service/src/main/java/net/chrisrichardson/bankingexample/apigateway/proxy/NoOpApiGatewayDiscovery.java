package net.chrisrichardson.bankingexample.apigateway.proxy;

import java.net.URI;

public class NoOpApiGatewayDiscovery implements ApiGatewayDiscovery {

  @Override
  public URI resolveLogicalHostName(URI url) {
    return url;
  }
}
