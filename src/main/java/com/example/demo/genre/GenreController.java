
package com.example.demo.genre;

import com.example.demo.creator.Creator;
import com.example.demo.creator.CreatorRepository;
import com.example.demo.game.Game;
import com.example.demo.game.GameRepository;
import java.util.List;
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
@RequestMapping({"/Genres"})
public class GenreController {
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    CreatorRepository creatorRepository;

    public GenreController() {
    }

    @GetMapping
    List<Genre> getGenres() {
        return this.genreRepository.findAll();
    }

    @PostMapping
    Genre createGenre(@RequestBody Genre genre) {
        return (Genre)this.genreRepository.save(genre);
    }

    @PutMapping({"/{genreId}/students/{gameId}"})
    Genre addStudentToGenre(@PathVariable Long genreId, @PathVariable Long gameId) {
        Genre Genre = (Genre)this.genreRepository.findById(genreId).get();
        Game game = (Game)this.gameRepository.findById(gameId).get();
        Genre.putGames.add(game);
        return (Genre)this.genreRepository.save(Genre);
    }

    @PutMapping({"/{genreId}/Creator/{creatorId}"})
    Genre assignCreatorToGenre(@PathVariable Long genreId, @PathVariable Long creatorId) {
        Genre genre = (Genre)this.genreRepository.findById(genreId).get();
        Creator creator = (Creator)this.creatorRepository.findById(creatorId).get();
        genre.setCreator(creator);
        return (Genre)this.genreRepository.save(genre);
    }

    @DeleteMapping({"/{genreId}"})
    ResponseEntity<?> deleteGenre(@PathVariable Long genreId) {
        if (!this.genreRepository.existsById(genreId)) {
            return ResponseEntity.notFound().build();
        } else {
            this.genreRepository.deleteById(genreId);
            return ResponseEntity.noContent().build();
        }
    }
}
