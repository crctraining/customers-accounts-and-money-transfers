package net.chrisrichardson.bankingexample.customerviewservice.backend;

import io.eventuate.Int128;
import net.chrisrichardson.bankingexample.accountservice.common.AccountInfo;
import net.chrisrichardson.bankingexample.commondomain.Money;
import net.chrisrichardson.bankingexample.customerservice.common.CustomerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CustomerViewService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private MongoTemplate mongoTemplate;
  private CustomerViewRepository customerViewRepository;

  public CustomerViewService(MongoTemplate mongoTemplate, CustomerViewRepository customerViewRepository) {
    this.mongoTemplate = mongoTemplate;
    this.customerViewRepository = customerViewRepository;
  }


  public void createCustomer(String customerId, CustomerInfo customerInfo) {
    try {
      mongoTemplate.upsert(
              new Query(where("id").is(customerId).and("customerInfo").exists(false)),
              new Update().set("customerInfo", customerInfo),
              CustomerView.class);
    } catch (DuplicateKeyException e) {
      // DuplicateKeyException happens because it Document exists but doesn't have a customerInfo attribute
      mongoTemplate.updateFirst(
              new Query(where("id").is(customerId).and("customerInfo").exists(false)),
              new Update().set("customerInfo", customerInfo),
              CustomerView.class);
    }
  }

  public void openAccount(Int128 eventId, String accountId, AccountInfo accountInfo) {
    mongoTemplate.upsert(
            new Query(where("id").is(accountInfo.getCustomerId())),
            new Update()
                    .set(keyForChange(eventId, accountId), new AccountChange(AccountChangeType.OPEN, accountInfo.getBalance()))
                    , CustomerView.class);

  }

  private String keyForChange(Int128 eventId, String accountId) {
    return "accounts." + accountId + ".changes." + eventId.asString();
  }

  public void debitAccount(Int128 eventId, String accountId, String customerId, Money amount, Money newBalance, String transactionId) {
    mongoTemplate.upsert(
            new Query(where("id").is(customerId)),
            new Update()
                    .set(keyForChange(eventId, accountId), new AccountChange(AccountChangeType.DEBIT, amount, newBalance, transactionId))
            , CustomerView.class);

  }

  public void creditAccount(Int128 eventId, String accountId, String customerId, Money amount, Money newBalance, String transactionId) {
    mongoTemplate.upsert(
            new Query(where("id").is(customerId)),
            new Update()
                    .set(keyForChange(eventId, accountId), new AccountChange(AccountChangeType.CREDIT, amount, newBalance, transactionId))
            , CustomerView.class);

  }

  public CustomerView findByCustomerId(String customerId) {
    return customerViewRepository.findOne(customerId);
  }
}
