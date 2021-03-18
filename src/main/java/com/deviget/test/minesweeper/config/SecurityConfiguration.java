package com.deviget.test.minesweeper.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import javax.sql.DataSource;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final DataSource dataSource;
  private final UserDetailsService appUserDetailsService;

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(appUserDetailsService)
        .passwordEncoder(encoder())
        .and()
        .authenticationProvider(authenticationProvider())
        .jdbcAuthentication()
        .dataSource(dataSource);
  }

  @Override
  public void configure(final WebSecurity web) {
    web.ignoring()
        .antMatchers("/resources/**");
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/login")
        .permitAll()
        .and()
        .formLogin()
        .permitAll()
        .and()
        .csrf()
        .disable();
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(10);
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(appUserDetailsService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }

  @Bean
  public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
    return new SecurityEvaluationContextExtension();
  }

}