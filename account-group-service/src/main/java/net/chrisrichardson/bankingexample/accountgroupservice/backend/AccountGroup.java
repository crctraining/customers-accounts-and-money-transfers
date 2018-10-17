package net.chrisrichardson.bankingexample.accountgroupservice.backend;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.commands.*;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.events.*;

import java.util.List;

public class AccountGroup extends ReflectiveMutableCommandProcessingAggregate<AccountGroup, AccountGroupCommand> {

  private AccountGroupState state = AccountGroupState.NEW;
  private String name;
  private String parentId;

  public List<Event> process(CreateAccountGroup cmd) {
    throw new RuntimeException("not yet implemented");
  }

  public List<Event> process(CreateMissingParent cmd) {
    return EventUtil.events(new MissingParentCreated(cmd.getChildId()));
  }

  public List<Event> process(ConfirmAddedToParent cmd) {
    switch (state) {
      case CREATE_PENDING_PARENT_VALIDATION:
        return EventUtil.events(new CreationSucceeded());
      case CREATED:
        return EventUtil.events();
      default:
        throw new UnsupportedOperationException("Bad state: " + state);
    }
  }

  public List<Event> process(NoteParentMissing cmd) {
    switch (state) {
      case CREATE_PENDING_PARENT_VALIDATION:
        return EventUtil.events(new CreationFailedDueToMissingParent());
      case CREATION_FAILED:
        return EventUtil.events();
      default:
        throw new UnsupportedOperationException("Bad state: " + state);
    }
  }

  public List<Event> process(AddAccount cmd) {
    throw new RuntimeException("not yet implemented");
  }

  public List<Event> process(AddChildGroup cmd) {
    switch (state) {
      case CREATED:
        return EventUtil.events(new ChildGroupAdded(cmd.getChildId()));
      default:
        throw new UnsupportedOperationException("Bad state: " + state);
    }
  }

  public void apply(AccountGroupCreated event) {
    throw new RuntimeException("not yet implemented");
  }

  public void apply(AccountGroupCreatedPendingParentValidation event) {
    this.name = event.getName();
    this.parentId = event.getParentId();
    this.state = AccountGroupState.CREATE_PENDING_PARENT_VALIDATION;
  }

  public void apply(CreationSucceeded event) {
    this.state = AccountGroupState.CREATED;
  }

  public void apply(MissingParentCreated event) {
    this.state = AccountGroupState.MISSING_PARENT;
  }

  public void apply(ConfirmAddedToParent event) {
    this.state = AccountGroupState.CREATED;
  }

  public void apply(CreationFailedDueToMissingParent event) {
    this.state = AccountGroupState.CREATION_FAILED;
  }

  public void apply(ChildGroupAdded event) {
    // TODO
  }

  public void apply(AccountAdded event) {
    throw new RuntimeException("not yet implemented");
  }

  public AccountGroupState getState() {
    return state;
  }

  public String getName() {
    return name;
  }
}
