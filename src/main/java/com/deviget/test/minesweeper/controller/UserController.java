package com.deviget.test.minesweeper.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/principal")
public class UserController {

  @GetMapping
  public Principal retrievePrincipal(final Principal principal) {
    return principal;
  }
}
