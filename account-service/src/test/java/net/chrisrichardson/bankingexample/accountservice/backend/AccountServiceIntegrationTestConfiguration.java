package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.tram.inmemory.TramInMemoryConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AccountBackendConfiguration.class,
        TramInMemoryConfiguration.class
})
@EnableAutoConfiguration
public class AccountServiceIntegrationTestConfiguration {



}
