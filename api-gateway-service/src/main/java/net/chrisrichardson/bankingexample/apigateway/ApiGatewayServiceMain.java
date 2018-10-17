package net.chrisrichardson.bankingexample.apigateway;

import net.chrisrichardson.bankingexample.apigateway.proxy.ApiGatewayDiscovery;
import net.chrisrichardson.bankingexample.apigateway.proxy.ApiGatewayProperties;
import net.chrisrichardson.bankingexample.apigateway.proxy.EurekaApiGatewayDiscovery;
import net.chrisrichardson.bankingexample.apigateway.proxy.NoOpApiGatewayDiscovery;
import net.chrisrichardson.bankingexample.hystrix.HystrixConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({ApiGatewayProperties.class})
@Import({HystrixConfiguration.class})
@ComponentScans({@ComponentScan, @ComponentScan(basePackages = "net.chrisrichardson.bankingexample.eureka")})
public class ApiGatewayServiceMain {

  @Bean
  @ConditionalOnMissingClass("net.chrisrichardson.bankingexample.eureka.EurekaConfiguration")
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  @ConditionalOnMissingClass("net.chrisrichardson.bankingexample.eureka.EurekaConfiguration")
  public ApiGatewayDiscovery noOpApiGatewayDiscovery() {
    return new NoOpApiGatewayDiscovery();
  }

  @Bean
  @ConditionalOnClass(name="net.chrisrichardson.bankingexample.eureka.EurekaConfiguration")
  public ApiGatewayDiscovery eurekaApiGatewayDiscovery() {
    return new EurekaApiGatewayDiscovery();
  }

  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayServiceMain.class, args);
  }

}
