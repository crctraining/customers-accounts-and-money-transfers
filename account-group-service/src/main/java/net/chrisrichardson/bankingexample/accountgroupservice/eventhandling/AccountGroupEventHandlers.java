package net.chrisrichardson.bankingexample.accountgroupservice.eventhandling;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.AccountGroupService;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.events.AccountGroupCreatedPendingParentValidation;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.events.ChildGroupAdded;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.events.MissingParentCreated;
import org.springframework.beans.factory.annotation.Autowired;

@EventSubscriber(id = "AccountGroupEventHandlers")
public class AccountGroupEventHandlers {

  @Autowired
  private AccountGroupService accountGroupService;

  @EventHandlerMethod
  public void handleAccountGroupCreatedPendingParentValidation(DispatchedEvent<AccountGroupCreatedPendingParentValidation> ehc) {
    String parentId = ehc.getEvent().getParentId();
    if (parentId != null) {
      accountGroupService.verifyParentGroupExists(parentId, ehc.getEntityId());
    }
  }

  @EventHandlerMethod
  public void handleChildAdded(DispatchedEvent<ChildGroupAdded> ehc) {
    accountGroupService.noteChildAddedToParent(ehc.getEntityId(), ehc.getEvent().getChildId());
  }

  @EventHandlerMethod
  public void handleMissingParentCreated(DispatchedEvent<MissingParentCreated> ehc) {
    accountGroupService.noteParentMissing(ehc.getEntityId(), ehc.getEvent().getChildId());
  }
}
