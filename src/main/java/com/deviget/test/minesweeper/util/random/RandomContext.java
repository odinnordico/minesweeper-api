package com.deviget.test.minesweeper.util.random;

public interface RandomContext {

  static RandomContext createContext(final double min, final double max) {
    return new RandomContext() {

      @Override
      public double getMin() {
        return min;
      }

      @Override
      public double getMax() {
        return max;
      }
    };
  }

  double getMin();

  double getMax();
}