package net.chrisrichardson.bankingexample.customerviewservice.backend;

import net.chrisrichardson.bankingexample.customerviewservice.common.CustomerView;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerViewRepository extends MongoRepository<CustomerView, String> {
}
