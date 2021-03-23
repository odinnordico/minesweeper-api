package com.deviget.test.minesweeper.pattern.translators;

import com.deviget.test.minesweeper.domain.model.UserAppGame;
import com.deviget.test.minesweeper.model.Game;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class UserAppGameAndGameTranslator implements Translator<UserAppGame, Game> {

  @Override
  public Game to(final UserAppGame input) {
    final int gameLen = input.getCols() * input.getRows();
    return Game.load(
        input.getRows(),
        input.getCols(),
        input.getMines(),
        new Integer[gameLen],
        Stream.of(input.getPlayedBoard())
            .flatMap(Stream::of)
            .collect(Collectors.toList())
            .toArray(Integer[]::new),
        input.isEnded(),
        input.isLost(),
        Duration.ofSeconds(input.getDurationInSeconds()));
  }

  @Override
  public UserAppGame from(final Game output) {
    return UserAppGame.builder()
        .gameId(output.getId())
        .rows(output.getRows())
        .cols(output.getCols())
        .mines(output.getMines())
        .ended(output.isEnded())
        .lost(output.isLost())
        .durationInSeconds(output.getDuration().getSeconds())
        .playedBoard(output.getBoardAs2D(false))
        .build();
  }
}
