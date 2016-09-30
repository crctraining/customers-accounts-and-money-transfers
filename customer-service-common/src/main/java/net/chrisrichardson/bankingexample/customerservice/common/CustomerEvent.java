package net.chrisrichardson.bankingexample.customerservice.common;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "net.chrisrichardson.bankingexample.customerservice.backend.Customer")
public interface CustomerEvent extends Event {
}
