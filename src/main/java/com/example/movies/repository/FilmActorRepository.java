package com.example.movies.repository;

import com.example.movies.entity.Actor;
import com.example.movies.entity.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<FilmActor, Long> {
}
