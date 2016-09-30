package net.chrisrichardson.bankingexample.eureka;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableCircuitBreaker
public class EurekaConfiguration {
}
