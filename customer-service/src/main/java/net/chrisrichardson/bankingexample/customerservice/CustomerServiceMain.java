package net.chrisrichardson.bankingexample.customerservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import net.chrisrichardson.bankingexample.customerservice.web.CustomerWebConfiguration;
import net.chrisrichardson.bankingexample.eureka.EurekaConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.commonswagger.CommonSwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CustomerWebConfiguration.class,
        CommonSwaggerConfiguration.class,
        EurekaConfiguration.class,
        EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class CustomerServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(CustomerServiceMain.class, args);
  }
}
