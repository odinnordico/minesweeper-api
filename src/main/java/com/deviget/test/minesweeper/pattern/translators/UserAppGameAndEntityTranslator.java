package com.deviget.test.minesweeper.pattern.translators;

import com.deviget.test.minesweeper.domain.model.UserAppGame;
import com.deviget.test.minesweeper.persistence.model.GameEntity;
import com.deviget.test.minesweeper.util.JsonHelper;
import org.springframework.stereotype.Component;

@Component
public class UserAppGameAndEntityTranslator implements Translator<UserAppGame, GameEntity> {

  private static Integer[][] reshapeArray(final int rows, final int cols, final Integer[] array) {
    final Integer[][] array2d = new Integer[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        array2d[i][j] = array[(j * rows) + i];
      }
    }
    return array2d;
  }

  @Override
  public GameEntity to(final UserAppGame input) {
    return GameEntity.builder()
        .gameId(input.getGameId())
        .rows(input.getRows())
        .columns(input.getCols())
        .mines(input.getMines())
        .ended(input.isEnded())
        .lost(input.isLost())
        .durationInSeconds(input.getDurationInSeconds())
        .build();
  }

  @Override
  public UserAppGame from(final GameEntity output) {
    return UserAppGame.builder()
        .gameId(output.getGameId())
        .rows(output.getRows())
        .cols(output.getColumns())
        .mines(output.getMines())
        .ended(output.isEnded())
        .lost(output.isLost())
        .durationInSeconds(output.getDurationInSeconds())
        .playedBoard(
            reshapeArray(
                output.getRows(),
                output.getColumns(),
                JsonHelper.deserialize(output.getPlayedBoard())))
        .build();
  }
}
