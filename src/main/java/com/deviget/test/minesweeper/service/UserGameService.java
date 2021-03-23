package com.deviget.test.minesweeper.service;

import static java.lang.String.format;

import com.deviget.test.minesweeper.domain.model.GameCreationRequest;
import com.deviget.test.minesweeper.domain.model.UncoverCellRequest;
import com.deviget.test.minesweeper.domain.model.UserAppGame;
import com.deviget.test.minesweeper.model.Game;
import com.deviget.test.minesweeper.pattern.translators.Translator;
import com.deviget.test.minesweeper.persistence.model.GameEntity;
import com.deviget.test.minesweeper.persistence.model.User;
import com.deviget.test.minesweeper.persistence.model.UserGame;
import com.deviget.test.minesweeper.persistence.repository.GameRepository;
import com.deviget.test.minesweeper.persistence.repository.UserGameRepository;
import com.deviget.test.minesweeper.persistence.repository.UserRepository;
import com.deviget.test.minesweeper.util.JsonHelper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserGameService {

  private final GameRepository gameRepository;
  private final UserRepository userRepository;
  private final UserGameRepository userGameRepository;
  private final Translator<GameEntity, Game> gameEntityAndGameTranslator;
  private final Translator<UserAppGame, GameEntity> userAppGameAndEntityTranslator;
  private final Translator<UserAppGame, Game> userAppGameAndGameTranslator;
  private final GameHandlerService gameHandlerService;

  @Transactional
  public UserAppGame createGame(final GameCreationRequest request, final String username) {
    final Game game = new Game(request.getRows(), request.getCols(), request.getMines());
    final UserAppGame userAppGame =
        UserAppGame.builder()
            .rows(game.getRows())
            .cols(game.getCols())
            .mines(game.getMines())
            .playedBoard(game.getBoardAs2D(false))
            .ended(game.isEnded())
            .lost(game.isLost())
            .build();
    final GameEntity gameEntity = gameEntityAndGameTranslator.from(game);
    final User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException(format("User %s not found", username)));
    gameEntity.setUser(user);
    gameEntity.setName(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    gameRepository.save(gameEntity);
    userGameRepository.save(
        UserGame.builder().username(user.getUsername()).gameId(gameEntity.getGameId()).build());
    userAppGame.setGameId(gameEntity.getGameId());
    return userAppGame;
  }

  public List<UserAppGame> getUserGames(final String username) {
    return gameRepository.findGameEntitiesByUserUsername(username).stream()
        .map(ge -> userAppGameAndEntityTranslator.from(ge))
        .collect(Collectors.toList());
  }

  public Optional<UserAppGame> getUserGame(final long gameId, final String username) {
    return gameRepository
        .findByGameIdAndUserUsername(gameId, username)
        .map(userAppGameAndEntityTranslator::from);
  }

  @Transactional
  public Optional<UserAppGame> uncoverCell(
      final UncoverCellRequest uncoverCellRequest, final String username) {
    final Optional<GameEntity> gameEntity =
        gameRepository.findByGameIdAndUserUsername(uncoverCellRequest.getGameId(), username);
    if (!gameEntity.isPresent()) {
      return Optional.empty();
    }
    final GameEntity entity = gameEntity.get();
    final Game game =
        gameEntity
            .map(gameEntityAndGameTranslator::to)
            .orElseThrow(() -> new IllegalArgumentException("Unable to process request"));
    validateCellRequest(uncoverCellRequest.getRow(), uncoverCellRequest.getCol(), game);
    gameHandlerService.updatePosition(
        uncoverCellRequest.getRow(),
        uncoverCellRequest.getCol(),
        uncoverCellRequest.isFlag(),
        game);
    entity.setPlayedBoard(JsonHelper.serialize(game.getPlayedBoard()));
    entity.setEnded(game.isEnded());
    entity.setLost(game.isLost());
    gameRepository.save(entity);
    return Optional.of(userAppGameAndGameTranslator.from(game));
  }

  private void validateCellRequest(final int row, final int col, @NotNull final Game game) {
    if (row >= game.getRows() || col >= game.getCols() || row < 0 || col < 0) {
      throw new IllegalArgumentException("Please verify the cell coordinates");
    }
  }
}
