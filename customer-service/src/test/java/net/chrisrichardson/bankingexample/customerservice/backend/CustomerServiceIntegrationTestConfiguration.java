package net.chrisrichardson.bankingexample.customerservice.backend;

import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CustomerBackendConfiguration.class, EmbeddedTestAggregateStoreConfiguration.class})
@EnableAutoConfiguration
public class CustomerServiceIntegrationTestConfiguration {



}
