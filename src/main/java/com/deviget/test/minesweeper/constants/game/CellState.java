package com.deviget.test.minesweeper.constants.game;

import static java.util.Optional.ofNullable;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CellState {
  // Actual Cell State
  COVERED(null),
  FLAGGED(-1),
  // Cell Value, means UNCOVERED Cell
  MINE(10),
  ZERO(0),
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8);
  private final Integer value;

  public static CellState loadFromValue(final int from) {
    return Arrays.stream(values())
        .filter(v -> ofNullable(v.getValue()).map(Integer::intValue).orElse(-2) == from)
        .findFirst()
        .orElse(null);
  }
}
