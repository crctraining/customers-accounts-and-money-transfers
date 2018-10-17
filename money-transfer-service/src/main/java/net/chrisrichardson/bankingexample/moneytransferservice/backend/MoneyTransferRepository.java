package net.chrisrichardson.bankingexample.moneytransferservice.backend;

import org.springframework.data.repository.CrudRepository;

public interface MoneyTransferRepository extends CrudRepository<MoneyTransfer, Long> {
}
