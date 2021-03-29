package com.example.movies.dataFactory;

import com.example.movies.entity.Film;
import com.example.movies.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmTestFactory {

    @Autowired
    private FilmRepository filmRepository;

    public void saveFilm(String test1) {
        Film film = new Film();
        film.setTitle(test1);
        filmRepository.save(film);
    }

    public void saveFilm(Film film) {

        if(film.getTitle()==null) film.setTitle("DefaultTitle");

        filmRepository.save(film);
    }

}
