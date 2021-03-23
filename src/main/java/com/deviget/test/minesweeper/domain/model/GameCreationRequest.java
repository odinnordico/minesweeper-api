package com.deviget.test.minesweeper.domain.model;

import static com.deviget.test.minesweeper.constants.game.GameConstant.MAX_COLS;
import static com.deviget.test.minesweeper.constants.game.GameConstant.MAX_MINES;
import static com.deviget.test.minesweeper.constants.game.GameConstant.MAX_ROWS;
import static com.deviget.test.minesweeper.constants.game.GameConstant.MIN_COLS;
import static com.deviget.test.minesweeper.constants.game.GameConstant.MIN_MINES;
import static com.deviget.test.minesweeper.constants.game.GameConstant.MIN_ROWS;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class GameCreationRequest {

  @Size(
      min = MIN_ROWS,
      max = MAX_ROWS,
      message = "Any game should have at least 4 and up to 80 rows")
  private int rows;

  @Size(
      min = MIN_COLS,
      max = MAX_COLS,
      message = "Any game should have at least 4 columns and up to 80")
  private int cols;

  @Size(
      min = MIN_MINES,
      max = MAX_MINES,
      message = "Any game should have at least 1 mine and up to 6000")
  private int mines;
}
