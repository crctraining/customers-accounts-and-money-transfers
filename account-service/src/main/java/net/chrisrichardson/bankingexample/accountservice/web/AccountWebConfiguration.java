package net.chrisrichardson.bankingexample.accountservice.web;

import net.chrisrichardson.bankingexample.accountservice.backend.AccountBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AccountBackendConfiguration.class)
@ComponentScan
public class AccountWebConfiguration {
}
