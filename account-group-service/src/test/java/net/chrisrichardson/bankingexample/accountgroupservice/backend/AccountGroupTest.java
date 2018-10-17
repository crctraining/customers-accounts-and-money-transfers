package net.chrisrichardson.bankingexample.accountgroupservice.backend;

import io.eventuate.testutil.AggregateTest;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.commands.*;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.commands.*;
import org.junit.Test;

public class AccountGroupTest extends AggregateTest<AccountGroup, AccountGroupCommand> {

  public AccountGroupTest() {
    super(AccountGroup.class);
  }

  private String parentId = "parent-1";
  private String name = "group-name";
  private String childId = "child-1";
  private String accountId = "accountId-1";

  @Test
  public void testCreate() {
    create(new CreateAccountGroup(parentId, name));
    update(new ConfirmAddedToParent());
    update(new AddAccount(accountId));
    update(new AddChildGroup(childId));
  }


  @Test
  public void testCreateMissingParent() {
    create(new CreateMissingParent(childId));
  }
}