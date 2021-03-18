package com.deviget.test.minesweeper.service.security;

import com.deviget.test.minesweeper.model.UserPrincipal;
import com.deviget.test.minesweeper.persistence.model.User;
import com.deviget.test.minesweeper.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@AllArgsConstructor
@Service
public class AppUserDetailService implements UserDetailsService {

  private final WebApplicationContext applicationContext;
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return UserPrincipal.builder().user(
        userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username))
    ).build();
  }
}
