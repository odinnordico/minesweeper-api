package com.deviget.test.minesweeper.model;

import com.deviget.test.minesweeper.constants.game.CellState;
import java.util.Arrays;

public class GameHelper {


  public static int countMines(final Game game, final Integer[] adjacency) {
    return (int)
        Arrays.stream(adjacency)
            .filter(v -> game.getFullBoard()[v] == CellState.MINE.getValue())
            .count();
  }
}
