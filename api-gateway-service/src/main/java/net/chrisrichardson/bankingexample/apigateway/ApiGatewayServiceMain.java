package net.chrisrichardson.bankingexample.apigateway;

import net.chrisrichardson.bankingexample.apigateway.proxy.ApiGatewayDiscovery;
import net.chrisrichardson.bankingexample.apigateway.proxy.ApiGatewayProperties;
import net.chrisrichardson.bankingexample.apigateway.proxy.EurekaApiGatewayDiscovery;
import net.chrisrichardson.bankingexample.apigateway.proxy.NoOpApiGatewayDiscovery;
import net.chrisrichardson.bankingexample.eureka.EurekaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableConfigurationProperties({ApiGatewayProperties.class})
@Import(EurekaConfiguration.class)
public class ApiGatewayServiceMain {

  @Bean
  @Profile("!eurekaServiceDiscovery")
  public ApiGatewayDiscovery noopApiGatewayDiscovery() {
    return new NoOpApiGatewayDiscovery();
  }

  @Bean
  @Profile("eurekaServiceDiscovery")
  public ApiGatewayDiscovery eurekaApiGatewayDiscovery() {
    return new EurekaApiGatewayDiscovery();
  }

  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayServiceMain.class, args);
  }

}
