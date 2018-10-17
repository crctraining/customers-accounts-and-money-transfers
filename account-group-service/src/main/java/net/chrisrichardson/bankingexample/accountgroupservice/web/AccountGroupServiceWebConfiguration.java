package net.chrisrichardson.bankingexample.accountgroupservice.web;

import net.chrisrichardson.bankingexample.accountgroupservice.eventhandling.AccountGroupEventHandlingConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AccountGroupEventHandlingConfiguration.class)
@ComponentScan
public class AccountGroupServiceWebConfiguration {
}
