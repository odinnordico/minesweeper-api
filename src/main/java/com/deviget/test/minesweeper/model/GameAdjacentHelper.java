package com.deviget.test.minesweeper.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameAdjacentHelper {

  private static final int MAX_ADJACENT = 8;

  public static Integer[] getAdjacentPositions(final int pos, final Game game) {

    final boolean firstRow = pos < game.getCols();
    final boolean lastRow = pos > game.getBoardLen() - game.getCols() - 1;
    final boolean firstCol = (pos % game.getCols()) == 0;
    final boolean lastCol = ((pos + 1 - game.getCols()) % game.getCols()) == 0;

    final List<Integer> adjacently =
        Stream.of(
            getAdjacentUpLeft(firstRow, firstCol, pos, game),
            getAdjacentUp(firstRow, pos, game),
            getAdjacentUpRight(firstRow, lastCol, pos, game),
            getAdjacentLeft(firstCol, pos, game),
            getAdjacentRight(lastCol, pos, game),
            getAdjacentDownLeft(lastRow, firstCol, pos, game),
            getAdjacentDown(lastRow, pos, game),
            getAdjacentDownRight(lastRow, lastCol, pos, game))
            .filter(Objects::nonNull)
            .filter(v -> v < game.getBoardLen())
            .collect(Collectors.toList());
    return adjacently.toArray(new Integer[adjacently.size()]);
  }

  private static Integer getAdjacentUpLeft(
      final boolean firstRow, final boolean firstCol, final int pos, final Game game) {
    return firstRow || firstCol ? null : pos - 1 - game.getCols();
  }

  private static Integer getAdjacentUp(final boolean firstRow, final int pos, final Game game) {
    return firstRow ? null : pos - game.getCols();
  }

  private static Integer getAdjacentUpRight(
      final boolean firstRow, final boolean lastCol, final int pos, final Game game) {
    return firstRow || lastCol ? null : pos + 1 - game.getCols();
  }

  private static Integer getAdjacentLeft(final boolean firstCol, final int pos, final Game game) {
    return firstCol ? null : pos - 1;
  }

  private static Integer getAdjacentRight(final boolean lasCol, final int pos, final Game game) {
    return lasCol ? null : pos + 1;
  }

  private static Integer getAdjacentDownLeft(
      final boolean lastRow, final boolean firstCol, final int pos, final Game game) {
    return lastRow || firstCol ? null : pos - 1 + game.getCols();
  }

  private static Integer getAdjacentDown(final boolean firstRow, final int pos, final Game game) {
    return firstRow ? null : pos + game.getCols();
  }

  private static Integer getAdjacentDownRight(
      final boolean lastRow, final boolean lastCol, final int pos, final Game game) {
    return lastRow || lastCol ? null : pos + 1 + game.getCols();
  }
}
