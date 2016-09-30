package net.chrisrichardson.bankingexample.apigateway.proxy;

import java.util.Arrays;

public class HttpSpec {


  // https://tools.ietf.org/html/draft-ietf-httpbis-p1-messaging-14#section-7.1.3

  public static String[] HOP_BY_HOP_HEADERS = {
          "Content-Length",
          "Connection",
          "Keep-Alive",
          "Proxy-Authenticate",
          "Proxy-Authorization",
          "TE",
          "Trailer",
          "Transfer-Encoding",
          "Upgrade"
  };

  public static boolean isHopByHopHeader(String header) {
    return Arrays.stream(HOP_BY_HOP_HEADERS).filter(x -> x.equalsIgnoreCase(header)).findFirst().isPresent();
  }
}
