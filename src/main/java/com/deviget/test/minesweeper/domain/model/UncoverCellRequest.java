package com.deviget.test.minesweeper.domain.model;

import static com.deviget.test.minesweeper.constants.game.GameConstant.MAX_ROWS;
import static com.deviget.test.minesweeper.constants.game.GameConstant.MIN_ROWS;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class UncoverCellRequest {

  @NotNull(message = "Game identification is required to perform a move")
  private Long gameId;

  @Min(value = MIN_ROWS, message = "Any game should have at least 4 rows")
  @Max(value = MAX_ROWS, message = "Any game should have up to 500 rows")
  private int row;

  @Size(
      min = MIN_ROWS,
      max = MAX_ROWS,
      message = "Any game should have at least 4 and up to 80 rows")
  private int col;

  private boolean flag;
}
