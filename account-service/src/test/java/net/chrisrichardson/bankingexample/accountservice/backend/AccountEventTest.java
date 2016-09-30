package net.chrisrichardson.bankingexample.accountservice.backend;

import io.eventuate.Event;
import io.eventuate.javaclient.spring.EventEntityUtil;
import net.chrisrichardson.bankingexample.accountservice.common.events.AccountEvent;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Set;

import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;

public class AccountEventTest {

  @Test
  public void eventClassesShouldReferenceAggregate() {
    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new AssignableTypeFilter(AccountEvent.class));
    Set<BeanDefinition> eventClasses = scanner.findCandidateComponents(AccountEvent.class.getPackage().getName());
    assertEquals(singleton(Account.class), eventClasses.stream().map(x -> {
      try {
        Class<Event> eventType = (Class<Event>) getClass().getClassLoader().loadClass(x.getBeanClassName());
        return EventEntityUtil.toEntityType(eventType);
      } catch (RuntimeException | ClassNotFoundException e) {
        throw new RuntimeException("Misconfigured event class: " + x.getBeanClassName(), e);
      }
    }).collect(toSet()));
  }


}
