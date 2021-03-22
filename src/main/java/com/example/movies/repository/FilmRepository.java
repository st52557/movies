package com.example.movies.repository;


import com.example.movies.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    Film findFilmByTitleContains(String string);

    void deleteFilmByTitle(String string);

    List<Film>findAll();

    List<Film>findFilmsByTitleContaining(String string);

    @Query(" select p from Film p where p.id between :from and :to")
    List<Film> findFilmByIdBetween(@Param("from")int from,@Param("to") int to);

    @Query(" select p from Film p where p.year between :from and :to")
    List<Film> findFilmByYearBetween(@Param("from")int from,@Param("to") int to);


}
