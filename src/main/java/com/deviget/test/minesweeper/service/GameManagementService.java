package com.deviget.test.minesweeper.service;

import static com.deviget.test.minesweeper.model.GameAdjacentHelper.getAdjacentPositions;

import com.deviget.test.minesweeper.constants.game.CellState;
import com.deviget.test.minesweeper.model.Game;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GameManagementService {

  public void updatePosition(final int row, final int col, final boolean flag, final Game game) {
    if (game.isEnded()) {
      return;
    }
    final int pos = (row * game.getCols()) + col;
    if (pos >= game.getBoardLen()) {
      return;
    }
    uncoverCell(pos, game);
  }

  private void uncoverCell(final int position, final Game game) {
    if (game.getFullBoard()[position] == CellState.MINE.getValue()) {
      handleMineUncovered(game);
    } else {
      handleUncoverCell(position, game);
    }
  }

  private void handleMineUncovered(final Game game) {
    game.markAsLost();
    for (int i = 0; i < game.getBoardLen(); i++) {
      if (game.getFullBoard()[i] == CellState.MINE.getValue()) {
        game.getPlayedBoard()[i] = CellState.MINE.getValue();
      }
    }
  }

  private void handleUncoverCell(final int pos, final Game game) {
    final Integer[] adjacency = getAdjacentPositions(pos, game);
    final int minesCount = countMines(game, adjacency);
    final CellState state = CellState.loadFromValue(minesCount);
    game.getFullBoard()[pos] = state.getValue();
    game.getPlayedBoard()[pos] = state.getValue();
    if (minesCount == 0) {
      for (final Integer adjPos : adjacency) {
        if (game.getPlayedBoard()[adjPos] == CellState.COVERED.getValue()) {
          uncoverCell(adjPos, game);
        }
      }
    }
  }

  private int countMines(final Game game, final Integer[] adjacency) {
    return (int)
        Arrays.stream(adjacency)
            .filter(v -> game.getFullBoard()[v] == CellState.MINE.getValue())
            .count();
  }
}
