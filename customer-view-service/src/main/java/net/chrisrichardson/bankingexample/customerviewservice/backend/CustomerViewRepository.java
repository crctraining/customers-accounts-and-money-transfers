package net.chrisrichardson.bankingexample.customerviewservice.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerViewRepository extends MongoRepository<CustomerView, String> {
}
