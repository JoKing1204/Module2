package com.example.demo.game;

import com.example.demo.genre.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;
    private String game;
    @JsonIgnore
    @ManyToMany(
            mappedBy = "putGames"
    )
    private Set<Genre> genres = new HashSet();

    public Game() {
    }

    public Long getId() {
        return this.id;
    }

    public String getGame() {
        return this.game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Set<Genre> getGenres() {
        return this.genres;
    }
}
