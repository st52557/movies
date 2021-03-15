package com.example.movies.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String genre;

    @OneToMany(mappedBy = "id")
    private Set<FilmGenre> filmGenres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<FilmGenre> getFilmGenres() {
        return filmGenres;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
