package net.chrisrichardson.bankingexample.apigateway.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class EurekaApiGatewayDiscovery implements ApiGatewayDiscovery {

  @Autowired
  private DiscoveryClient discoveryClient;

  @Override
  public URI resolveLogicalHostName(URI url) throws URISyntaxException {
    List<ServiceInstance> list = discoveryClient.getInstances(url.getHost());
    if (list != null && list.size() > 0 ) {
      URI targetUri = list.get(0).getUri();
      return new URI(targetUri.getScheme(), targetUri.getUserInfo(), targetUri.getHost(), targetUri.getPort(), url.getPath(), url.getQuery(), url.getFragment());
    }
    throw new NoServiceInstancesAvailableException(url);
  }
}
