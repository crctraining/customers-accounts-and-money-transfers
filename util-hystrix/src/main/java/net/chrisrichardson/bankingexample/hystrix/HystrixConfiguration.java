package net.chrisrichardson.bankingexample.hystrix;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCircuitBreaker
public class HystrixConfiguration {
}
