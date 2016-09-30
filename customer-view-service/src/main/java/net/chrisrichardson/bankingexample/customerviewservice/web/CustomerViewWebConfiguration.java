package net.chrisrichardson.bankingexample.customerviewservice.web;

import net.chrisrichardson.bankingexample.customerviewservice.backend.CustomerViewBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CustomerViewBackendConfiguration.class)
@ComponentScan
public class CustomerViewWebConfiguration {
}
