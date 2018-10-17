package net.chrisrichardson.bankingexample.accountgroupservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import net.chrisrichardson.bankingexample.accountgroupservice.web.AccountGroupServiceWebConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.commonswagger.CommonSwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({AccountGroupServiceWebConfiguration.class, CommonSwaggerConfiguration.class, EventuateDriverConfiguration.class})
public class AccountGroupServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(AccountGroupServiceMain.class, args);
  }
}
