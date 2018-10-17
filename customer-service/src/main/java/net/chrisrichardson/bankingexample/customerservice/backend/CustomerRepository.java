package net.chrisrichardson.bankingexample.customerservice.backend;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
