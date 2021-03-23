package com.deviget.test.minesweeper.controller;

import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/principal")
public class UserController {

  @GetMapping
  public ResponseEntity<Principal> retrievePrincipal(final Principal principal) {
    return ResponseEntity.ok(principal);
  }
}
