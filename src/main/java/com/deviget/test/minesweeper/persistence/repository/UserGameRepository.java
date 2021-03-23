package com.deviget.test.minesweeper.persistence.repository;

import com.deviget.test.minesweeper.persistence.model.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Long> {

}
