package net.chrisrichardson.bankingexample.moneytransferservice.common.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "net.chrisrichardson.bankingexample.moneytransferservice.backend.MoneyTransfer")
public interface MoneyTransferEvent extends Event {
}
