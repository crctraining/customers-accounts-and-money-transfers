package net.chrisrichardson.bankingexample.apigateway.proxy;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ResponseFactory {

  public ResponseEntity<String> makeResponse(HttpResponse proxiedResponse) throws IOException {
    return new ResponseEntity<>(makeResponseBody(proxiedResponse),
            makeResponseHeaders(proxiedResponse),
            HttpStatus.valueOf(proxiedResponse.getStatusLine().getStatusCode()));
  }

  private HttpHeaders makeResponseHeaders(HttpResponse proxiedResponse) {
    HttpHeaders result = new HttpHeaders();
    HeaderIterator it = proxiedResponse.headerIterator();
    while (it.hasNext()) {
      Header header = it.nextHeader();
      if (!HttpSpec.isHopByHopHeader(header.getName()))
        result.add(header.getName(), header.getValue());
    }
    return result;
  }

  private String makeResponseBody(HttpResponse proxiedResponse) throws IOException {
    return IOUtils.toString(proxiedResponse.getEntity().getContent());
  }


}
