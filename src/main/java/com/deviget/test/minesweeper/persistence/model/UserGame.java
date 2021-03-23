package com.deviget.test.minesweeper.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_GAME")
@IdClass(UserGamePk.class)
public class UserGame {

  @Id
  private String username;
  @Id
  private long gameId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "username", insertable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gameId", insertable = false, updatable = false)
  private GameEntity game;
}
