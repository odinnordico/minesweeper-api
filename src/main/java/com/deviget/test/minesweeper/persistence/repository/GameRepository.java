package com.deviget.test.minesweeper.persistence.repository;

import com.deviget.test.minesweeper.persistence.model.GameEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {

  Optional<GameEntity> findByGameIdAndUserUsername(long gameId, String username);

  List<GameEntity> findGameEntitiesByUserUsername(String username);
}
