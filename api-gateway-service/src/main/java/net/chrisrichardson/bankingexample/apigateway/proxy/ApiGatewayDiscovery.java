package net.chrisrichardson.bankingexample.apigateway.proxy;

import java.net.URI;
import java.net.URISyntaxException;

public interface ApiGatewayDiscovery {
  URI resolveLogicalHostName(URI url) throws URISyntaxException;
}

