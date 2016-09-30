package net.chrisrichardson.bankingexample.apigateway.proxy;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "api.gateway")
public class ApiGatewayProperties {

  private List<Endpoint> endpoints;

  public Endpoint findEndpoint(String requestURI, String method) {
    return getEndpoints().stream()
            .filter(e -> e.matches(requestURI, method))
            .findFirst().orElseThrow(() -> new NoProxyTargetFoundException(String.format("Can't find target for %s %s", requestURI, method)));
  }

  public static class Endpoint {
    private String path;
    private String method;
    private String location;

    public boolean matches(String requestURI, String method) {
      return requestURI.matches(this.path) && method.matches(this.method);
    }

    public Endpoint() {
    }

    public Endpoint(String location) {
      this.location = location;
    }

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }

    public String getMethod() {
      return method;
    }

    public void setMethod(String method) {
      this.method = method;
    }

    public String getLocation() {
      return location;
    }

    public void setLocation(String location) {
      this.location = location;
    }
  }

  public List<Endpoint> getEndpoints() {
    return endpoints;
  }

  public void setEndpoints(List<Endpoint> endpoints) {
    this.endpoints = endpoints;
  }
}
