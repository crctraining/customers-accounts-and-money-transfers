package net.chrisrichardson.bankingexample.customerservice.web;

import net.chrisrichardson.bankingexample.customerservice.backend.CustomerBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CustomerBackendConfiguration.class)
@ComponentScan
public class CustomerWebConfiguration {
}
