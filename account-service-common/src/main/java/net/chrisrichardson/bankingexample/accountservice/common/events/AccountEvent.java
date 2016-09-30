package net.chrisrichardson.bankingexample.accountservice.common.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "net.chrisrichardson.bankingexample.accountservice.backend.Account")
public interface AccountEvent extends Event {
}
