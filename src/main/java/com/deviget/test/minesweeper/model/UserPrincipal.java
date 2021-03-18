package com.deviget.test.minesweeper.model;

import com.deviget.test.minesweeper.persistence.model.Authorities;
import com.deviget.test.minesweeper.persistence.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

  private final User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getAuthorities().stream().map(Authorities::getAuthority).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return "***";
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return user.isEnabled();
  }

  @Override
  public boolean isAccountNonLocked() {
    return user.isEnabled();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return user.isEnabled();
  }

  @Override
  public boolean isEnabled() {
    return user.isEnabled();
  }
}
