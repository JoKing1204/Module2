package com.example.demo.genre;

import com.example.demo.creator.Creator;
import com.example.demo.game.Game;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "game_put",
            joinColumns = {@JoinColumn(
                    name = "genre_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "game_id"
            )}
    )
    Set<Game> putGames = new HashSet();
    @ManyToOne(
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "creator_id",
            referencedColumnName = "id"
    )
    private Creator creator;

    public Genre() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getPutGames() {
        return this.putGames;
    }

    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
