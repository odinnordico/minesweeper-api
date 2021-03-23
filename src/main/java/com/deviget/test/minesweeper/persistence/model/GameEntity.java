package com.deviget.test.minesweeper.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Entity
@Table(name = "GAME")
public class GameEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long gameId;

  @Column(length = 255)
  private String name;

  @Column(length = 2)
  private int rows;

  @Column(length = 2)
  private int columns;

  @Column(length = 3)
  private int mines;

  private boolean ended;
  private boolean lost;
  private long durationInSeconds;

  @Column(length = 512)
  private String fullBoard;
  @Column(length = 512)
  private String playedBoard;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "username")
  private User user;

}
