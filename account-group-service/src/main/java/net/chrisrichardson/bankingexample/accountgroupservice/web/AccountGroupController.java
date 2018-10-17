package net.chrisrichardson.bankingexample.accountgroupservice.web;

import net.chrisrichardson.bankingexample.accountgroupservice.backend.AccountGroup;
import net.chrisrichardson.bankingexample.accountgroupservice.backend.AccountGroupService;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.AccountGroupInfo;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.CreateAccountGroupResponse;
import net.chrisrichardson.bankingexample.accountgroupservice.common.web.GetAccountGroupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/api/accountgroups")
public class AccountGroupController {

  @Autowired
  private AccountGroupService accountGroupService;

  @RequestMapping(method = RequestMethod.POST)
  public CreateAccountGroupResponse createAccountGroup(@Validated @RequestBody AccountGroupInfo accountGroupInfo) {
    assertNotNull(accountGroupInfo);
    return new CreateAccountGroupResponse(accountGroupService.createAccountGroup(accountGroupInfo).getEntityId());
  }

  @RequestMapping(path="/{accountGroupId}", method = RequestMethod.GET)
  public GetAccountGroupResponse getAccountGroup(@PathVariable String accountGroupId) {
    AccountGroup ag = accountGroupService.findAccountGroup(accountGroupId).getEntity();
    return new GetAccountGroupResponse(ag.getState().name(), ag.getName());
  }

}
