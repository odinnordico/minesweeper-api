package com.deviget.test.minesweeper.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
  @Id
  private String username;
  private String displayName;
  private String password;
  private boolean enabled = true;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Authorities> authorities;
}
