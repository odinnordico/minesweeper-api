package com.deviget.test.minesweeper.persistence.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  @Default
  private boolean enabled = true;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Authorities> authorities;
}
