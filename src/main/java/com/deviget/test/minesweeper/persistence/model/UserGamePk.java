package com.deviget.test.minesweeper.persistence.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserGamePk implements Serializable {

  private String username;
  private long gameId;
}
