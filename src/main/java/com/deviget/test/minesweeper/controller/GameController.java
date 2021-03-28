package com.deviget.test.minesweeper.controller;

import static java.lang.String.format;

import com.deviget.test.minesweeper.domain.model.GameCreationRequest;
import com.deviget.test.minesweeper.domain.model.UncoverCellRequest;
import com.deviget.test.minesweeper.domain.model.UserAppGame;
import com.deviget.test.minesweeper.service.UserGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {

  private final UserGameService userGameService;

  @Operation(summary = "Create a game for the authenticated user")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "201",
              description = "Game created",
              content = {
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UserAppGame.class))
              })
      })
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserAppGame> createGame(
      @Valid @RequestBody final GameCreationRequest gameCreationRequest,
      final Principal principal) {
    final UserAppGame userAppGame =
        userGameService.createGame(gameCreationRequest, principal.getName());
    return ResponseEntity.created(URI.create(format("/games/%d", userAppGame.getGameId())))
        .body(userAppGame);
  }

  @Operation(summary = "Retrieve a game by the given id for the logged user")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "The game was found and returned",
              content = {
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UserAppGame.class))
              }),
          @ApiResponse(
              responseCode = "404",
              description = "The given id is not present or not correspond to logged user")
      })
  @GetMapping(path = "/{game-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserAppGame> getUserGame(
      @PathVariable("game-id") final long gameId, final Principal principal) {

    return userGameService
        .getUserGame(gameId, principal.getName())
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Operation(summary = "Retrieve all the games of the logged user")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "All existing games of the user",
              content = {
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UserAppGame.class))
              })
      })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserAppGame>> getUserGames(final Principal principal) {
    return ResponseEntity.ok(userGameService.getUserGames(principal.getName()));
  }

  @Operation(summary = "Trigger an uncovering of a cell in a given name")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "The updated game",
              content = {
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UserAppGame.class))
              })
      })
  @PutMapping(
      path = "/{game-id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserAppGame> uncoverCell(
      @Valid @RequestBody final UncoverCellRequest uncoverCellRequest, final Principal principal) {
    return userGameService
        .uncoverCell(uncoverCellRequest, principal.getName())
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
