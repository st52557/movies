package com.example.movies.repository;

import com.example.movies.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(" select a from Actor a inner join FilmActor fa on a.id = fa.id inner join Film f on fa.id = f.id where f.title = :title")
    List<Actor> findAllActorsFromFilm(@Param("title") String film);

}
