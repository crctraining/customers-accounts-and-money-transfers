package net.chrisrichardson.bankingexample.moneytransferservice.web;

import net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransferBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MoneyTransferBackendConfiguration.class)
@ComponentScan
public class MoneyTransferWebConfiguration {
}
