package net.chrisrichardson.bankingexample.apigateway.controller;

import net.chrisrichardson.bankingexample.apigateway.proxy.NoProxyTargetFoundException;
import net.chrisrichardson.bankingexample.apigateway.proxy.NoServiceInstancesAvailableException;
import net.chrisrichardson.bankingexample.apigateway.proxy.ProxyRequestFactory;
import net.chrisrichardson.bankingexample.apigateway.proxy.ResponseFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class GatewayController {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ProxyRequestFactory proxyRequestFactory;

  @Autowired
  private ResponseFactory responseFactory;

  private HttpClient httpClient;

  @PostConstruct
  public void init() {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

    httpClient = HttpClients.custom()
            .setConnectionManager(cm)
            .build();
  }

  @RequestMapping(value = "/api/**", method = {GET, POST, DELETE})
  public ResponseEntity<String> proxyRequest(HttpServletRequest request) throws NoSuchRequestHandlingMethodException, IOException, URISyntaxException {
    logger.info("original request {} {}", request.getMethod(), request.getRequestURI());
    HttpUriRequest proxiedRequest = proxyRequestFactory.make(request);
    logger.info("request: {}", proxiedRequest);
    HttpResponse proxiedResponse;
    try {
      proxiedResponse = httpClient.execute(proxiedRequest);
    } catch (IOException e) {
      logger.error("Proxying error", e);
      throw new HttpClientIOException(e);
    } catch (RuntimeException e) {
      logger.error("Proxying runtime", e);
      throw e;
    }
    logger.info("Response {}", proxiedResponse.getStatusLine().getStatusCode());
    return responseFactory.makeResponse(proxiedResponse);
  }

  @ExceptionHandler(NoProxyTargetFoundException.class)
  @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No endpoint found")
  public void noProxyTargetFound() {}

  @ExceptionHandler({NoServiceInstancesAvailableException.class, HttpClientIOException.class})
  @ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE, reason="service unavailable")
  public void clientIOException() {}

}
