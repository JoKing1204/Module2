package com.example.demo.game;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/games"})
public class GameController {
    @Autowired
    GameRepository gameRepository;

    public GameController() {
    }

    @GetMapping
    List<Game> getGames() {
        return this.gameRepository.findAll();
    }
    @GetMapping(path ="{gameId}")
    Optional<Game> getGame(@PathVariable("gameId") Long id) {
        return this.gameRepository.findById(id);
    }

    @PostMapping
    Game createGame(@RequestBody Game Game) {
        return (Game)this.gameRepository.save(Game);
    }

    @DeleteMapping({"/{gameId}"})
    ResponseEntity<?> deleteGenre(@PathVariable Long gameId) {
        if (!this.gameRepository.existsById(gameId)) {
            return ResponseEntity.notFound().build();
        } else {
            this.gameRepository.deleteById(gameId);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping({"/{gameId}"})
    ResponseEntity<Game> changeGame(@PathVariable Long gameId, @RequestBody Game updatedGame) {
        if (!this.gameRepository.existsById(gameId)) {
            return ResponseEntity.notFound().build();
        } else {
            Game existingGame = (Game)this.gameRepository.findById(gameId).orElse((Game)null);
            if (existingGame == null) {
                return ResponseEntity.notFound().build();
            } else {
                existingGame.setGame(updatedGame.getGame());
                Game savedGame = (Game)this.gameRepository.save(existingGame);
                return ResponseEntity.ok(savedGame);
            }
        }
    }
}
