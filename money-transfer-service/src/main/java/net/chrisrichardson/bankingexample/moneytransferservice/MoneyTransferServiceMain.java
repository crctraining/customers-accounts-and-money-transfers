package net.chrisrichardson.bankingexample.moneytransferservice;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import net.chrisrichardson.bankingexample.moneytransferservice.messaging.MoneyTransferServiceMessagingConfiguration;
import net.chrisrichardson.bankingexample.moneytransferservice.web.MoneyTransferWebConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.commonswagger.CommonSwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MoneyTransferWebConfiguration.class, MoneyTransferServiceMessagingConfiguration.class, CommonSwaggerConfiguration.class, TramJdbcKafkaConfiguration.class})
@EnableAutoConfiguration
public class MoneyTransferServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferServiceMain.class, args);
    }
}
