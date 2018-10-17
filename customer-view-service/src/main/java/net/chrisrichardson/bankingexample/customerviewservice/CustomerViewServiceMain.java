package net.chrisrichardson.bankingexample.customerviewservice;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import net.chrisrichardson.bankingexample.customerviewservice.web.CustomerViewWebConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.commonswagger.CommonSwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CustomerViewWebConfiguration.class,
        CommonSwaggerConfiguration.class,
//        EurekaConfiguration.class,
        TramJdbcKafkaConfiguration.class})
@EnableAutoConfiguration
public class CustomerViewServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(CustomerViewServiceMain.class, args);
  }
}
