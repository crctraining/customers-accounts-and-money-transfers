package net.chrisrichardson.bankingexample.accountgroupservice.backend;

import io.eventuate.EntityAlreadyExistsException;
import io.eventuate.EntityNotFoundException;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import io.eventuate.sync.AggregateRepository;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.commands.*;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.AccountGroupInfo;

public class AccountGroupService {

  private AggregateRepository<AccountGroup, AccountGroupCommand> accountGroupRepository;

  public AccountGroupService(AggregateRepository<AccountGroup, AccountGroupCommand> accountGroupRepository) {
    this.accountGroupRepository = accountGroupRepository;
  }

  public EntityWithIdAndVersion<AccountGroup> createAccountGroup(AccountGroupInfo accountGroupInfo) {
    throw new RuntimeException("not yet implemented");
  }

  public EntityWithIdAndVersion<AccountGroup> addAccount(String accountGroupId, String accountId) {
    return accountGroupRepository.update(accountGroupId, new AddAccount(accountId));
  }

  public EntityWithMetadata<AccountGroup> findAccountGroup(String accountGroupId) {
    throw new RuntimeException("not yet implemented");
  }

  public EntityWithIdAndVersion<AccountGroup> verifyParentGroupExists(String parentId, String childId) {
    try {
      return accountGroupRepository.update(parentId, new AddChildGroup(childId));
    } catch (EntityNotFoundException enf) {
      try {
        return accountGroupRepository.save(new CreateMissingParent(childId));
      } catch (EntityAlreadyExistsException e) {
        return verifyParentGroupExists(parentId, childId);
      }
    }
  }

  public void noteChildAddedToParent(String parentId, String childId) {
    accountGroupRepository.update(childId, new ConfirmAddedToParent());
  }

  public void noteParentMissing(String parentId, String childId) {
    accountGroupRepository.update(childId, new NoteParentMissing());
  }
}
