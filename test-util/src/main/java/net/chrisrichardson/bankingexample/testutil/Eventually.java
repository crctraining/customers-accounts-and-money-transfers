package net.chrisrichardson.bankingexample.testutil;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Eventually {

  public static <T> T eventually(Supplier<T> supplier) {
    Throwable laste = null;
    for (int i = 0; i < 50 ; i++) {
      try {
        return supplier.get();
      } catch (Throwable t) {
        laste = t;
      }
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    if (laste != null)
      throw new RuntimeException("Last exception was", laste);
    else
      throw new RuntimeException("predicate never satisfied");

  }

  public static void eventually(Runnable supplier) {
    eventually(() -> {
       supplier.run();
      return null;
    });
  }
}
