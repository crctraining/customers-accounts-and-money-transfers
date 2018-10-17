package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.tram.inmemory.TramInMemoryConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CustomerBackendConfiguration.class, TramInMemoryConfiguration.class})
@EnableAutoConfiguration
public class CustomerServiceIntegrationTestConfiguration {



}
