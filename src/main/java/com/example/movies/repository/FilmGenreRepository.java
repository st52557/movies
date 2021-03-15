package com.example.movies.repository;

import com.example.movies.entity.FilmGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long> {


}
