package net.chrisrichardson.bankingexample.accountservice.backend;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
