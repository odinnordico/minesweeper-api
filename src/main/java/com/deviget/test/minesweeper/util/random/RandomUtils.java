package com.deviget.test.minesweeper.util.random;

import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtils {

  public static int getPositiveRandom(final RandomContext context) {
    if (context.getMax() <= 0d) {
      return 0;
    }
    final Random random = new Random(System.currentTimeMillis());
    return random.nextInt((int) context.getMax());
  }
}
