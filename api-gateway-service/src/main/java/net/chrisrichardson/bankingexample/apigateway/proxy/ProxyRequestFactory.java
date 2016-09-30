package net.chrisrichardson.bankingexample.apigateway.proxy;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

@Component
public class ProxyRequestFactory {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  private ApiGatewayProperties apiGatewayProperties;
  private ApiGatewayDiscovery apiGatewayDiscovery;

  @Autowired
  public ProxyRequestFactory(ApiGatewayProperties apiGatewayProperties, ApiGatewayDiscovery apiGatewayDiscovery) {
    this.apiGatewayProperties = apiGatewayProperties;
    this.apiGatewayDiscovery = apiGatewayDiscovery;
  }

  public HttpUriRequest make(HttpServletRequest request) throws URISyntaxException, IOException {

    RequestBuilder rb = RequestBuilder.create(request.getMethod());
    URI targetUrl = makeTargetUrl(request);
    logger.info("Target URL = {}", targetUrl);
    URI discoveredUrl = apiGatewayDiscovery.resolveLogicalHostName(targetUrl);
    logger.info("Discovered URL = {}", discoveredUrl);
    rb.setUri(discoveredUrl);

    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      String headerValue = request.getHeader(headerName);
      if (!HttpSpec.isHopByHopHeader(headerName)) {
        rb.addHeader(headerName, headerValue);
      }
    }

    String requestContent = IOUtils.toString(request.getInputStream());
    if (!requestContent.isEmpty()) {
      StringEntity entity = new StringEntity(requestContent, ContentType.APPLICATION_JSON);
      rb.setEntity(entity);
    }

    return rb.build();
  }

  private URI makeTargetUrl(HttpServletRequest request) throws URISyntaxException {
    String requestURI = request.getRequestURI();
    String baseTargetUrl = apiGatewayProperties.findEndpoint(requestURI, request.getMethod()).getLocation() + requestURI;
    if (StringUtils.isNotBlank(request.getQueryString())) {
      return new URI(baseTargetUrl + "?" + request.getQueryString());
    } else {
      return new URI(baseTargetUrl);
    }
  }

}
