package com.deviget.test.minesweeper.pattern.translators;

import com.deviget.test.minesweeper.model.Game;
import com.deviget.test.minesweeper.persistence.model.GameEntity;
import com.deviget.test.minesweeper.util.JsonHelper;
import java.time.Duration;
import org.springframework.stereotype.Component;

@Component
public class GameEntityAndGameTranslator implements Translator<GameEntity, Game> {

  @Override
  public Game to(final GameEntity input) {
    return Game.load(
        input.getRows(),
        input.getColumns(),
        input.getMines(),
        JsonHelper.deserialize(input.getFullBoard()),
        JsonHelper.deserialize(input.getPlayedBoard()),
        input.isEnded(),
        input.isLost(),
        Duration.ofSeconds(input.getDurationInSeconds()));
  }

  @Override
  public GameEntity from(final Game output) {
    return GameEntity.builder()
        .gameId(output.getId())
        .rows(output.getRows())
        .columns(output.getCols())
        .mines(output.getMines())
        .ended(output.isEnded())
        .lost(output.isLost())
        .durationInSeconds(output.getDuration().getSeconds())
        .fullBoard(JsonHelper.serialize(output.getFullBoard()))
        .playedBoard(JsonHelper.serialize(output.getPlayedBoard()))
        .build();
  }
}
