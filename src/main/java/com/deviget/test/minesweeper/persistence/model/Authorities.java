package com.deviget.test.minesweeper.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authorities {
  @Id
  private String username;
  @Enumerated(EnumType.STRING)
  private Role authority;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "username", insertable = false, updatable = false)
  private User user;
}
