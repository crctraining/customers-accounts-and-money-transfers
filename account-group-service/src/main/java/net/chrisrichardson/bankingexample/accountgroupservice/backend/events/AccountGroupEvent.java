package net.chrisrichardson.bankingexample.accountgroupservice.backend.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="net.chrisrichardson.bankingexample.accountgroupservice.backend.AccountGroup")
public interface AccountGroupEvent extends Event {
}
