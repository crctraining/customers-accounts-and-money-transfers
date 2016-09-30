package net.chrisrichardson.bankingexample.commondomain;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {

  @Test
  public void shouldAdd() {
    assertEquals(new Money("7"), new Money("6").add(new Money("1")));
  }

  @Test
  public void shouldSubtract() {
    assertEquals(new Money("5"), new Money("6").subtract(new Money("1")));
  }

  @Test
  public void shouldComputeIsGreaterThanOrEqual() {
    assertTrue(new Money("7").isGreaterOrEqualThan(new Money("6")));
    assertTrue(new Money("6").isGreaterOrEqualThan(new Money("6")));
    assertFalse(new Money("5").isGreaterOrEqualThan(new Money("6")));
  }
}