package com.deviget.test.minesweeper.model;

import static com.deviget.test.minesweeper.model.GameAdjacentHelper.getAdjacentPositions;
import static com.deviget.test.minesweeper.model.GameHelper.countMines;

import com.deviget.test.minesweeper.constants.game.CellState;
import com.deviget.test.minesweeper.util.random.RandomContext;
import com.deviget.test.minesweeper.util.random.RandomUtils;
import java.io.Serializable;
import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Game implements Serializable {

  private final int rows;
  private final int cols;
  private final int mines;
  private final int boardLen;
  private final Integer[] fullBoard;
  private final Integer[] playedBoard;
  private boolean ended;
  private boolean lost;
  @Setter
  private Duration duration;

  public Game(final int rows, final int cols, final int mines) {
    this.rows = rows;
    this.cols = cols;
    this.mines = mines;
    this.boardLen = rows * cols;
    this.fullBoard = new Integer[this.boardLen];
    this.playedBoard = new Integer[this.boardLen];
    this.ended = false;
    this.lost = false;
    this.duration = Duration.ZERO;
    this.initGame();
  }

  private Game(
      final int rows,
      final int cols,
      final int mines,
      final Integer[] fullBoard,
      final Integer[] playedBoard,
      final boolean ended,
      final boolean lost,
      final Duration duration,
      final int boardLen) {
    this.rows = rows;
    this.cols = cols;
    this.mines = mines;
    this.fullBoard = fullBoard;
    this.playedBoard = playedBoard;
    this.ended = ended;
    this.lost = lost;
    this.duration = duration;
    this.boardLen = boardLen;
  }

  public static Game load(
      final int rows,
      final int cols,
      final int mines,
      final Integer[] fullBoard,
      final Integer[] playedBoard,
      final boolean ended,
      final boolean lost,
      final Duration duration) {
    final int boardLen = rows * cols;
    if (boardLen != fullBoard.length || boardLen != playedBoard.length) {
      throw new IllegalArgumentException(
          "Unable to load game, rows and columns do not correspond with game board");
    }
    if (duration.isZero()) {
      throw new IllegalArgumentException("Unable to load game, duration game is corrupted");
    }
    return new Game(rows, cols, mines, fullBoard, playedBoard, ended, lost, duration, boardLen);
  }

  public void markAsLost() {
    this.lost = true;
    this.ended = true;
  }

  public void endGame() {
    this.ended = true;
  }

  public int getPosition(final int row, final int col) {
    return (row * this.cols) + col;
  }

  private void initGame() {
    setMines();
    setBoardValues();
  }

  private void setMines() {
    final RandomContext positionContext = RandomContext.createContext(0d, (double) this.boardLen);
    for (int i = 0; i < this.mines; i++) {
      final int position = RandomUtils.getPositiveRandom(positionContext);
      if (this.fullBoard[position] != CellState.MINE.getValue()) {
        this.fullBoard[position] = CellState.MINE.getValue();
      } else {
        i--;
      }
    }
  }

  private void setBoardValues() {
    for (int i = 0; i < this.boardLen; i++) {
      this.playedBoard[i] = CellState.COVERED.getValue();
      if (this.fullBoard[i] != CellState.MINE.getValue()) {
        final Integer[] adjacentPositions = getAdjacentPositions(i, this);
        final int cellMines = countMines(this, adjacentPositions);
        this.fullBoard[i] = CellState.loadFromValue(cellMines).getValue();
      }
    }
  }

  public Integer[][] getBoardAs2D(final boolean fullBoard) {
    final Integer[][] matrix2d = new Integer[this.rows][this.cols];
    int index = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.cols; j++) {
        matrix2d[i][j] = (fullBoard ? this.fullBoard : this.playedBoard)[index++];
      }
    }
    return matrix2d;
  }
}
