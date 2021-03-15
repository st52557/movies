package com.example.movies;


import com.example.movies.entity.Film;
import com.example.movies.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FilmRepositoryTest {


    @Autowired
    private FilmRepository filmRepository;


    @Test
    void newFilmTest() {

        int before = filmRepository.findAll().size();

        Film film = new Film();
        film.setTitle("Tmavomodrý svět");
        filmRepository.save(film);

        int after = filmRepository.findAll().size();

        Assertions.assertThat(after).isEqualTo(before+1);

    }

    @Test
    void findFilm() {

        Film film = new Film();
        film.setTitle("Kod Enigmy");
        filmRepository.save(film);

        Assertions.assertThat(filmRepository.findFilmByTitleContains("Kod Enigmy").getTitle()).isEqualTo("Kod Enigmy");

    }

    @Test
    void deleteFilm() {

        int before = filmRepository.findFilmsByTitleContaining("Tenet").size();

        Film film = new Film();
        film.setTitle("Tenet");
        filmRepository.save(film);

        Assertions.assertThat(filmRepository.findFilmByTitleContains("Tenet").getTitle()).isEqualTo("Tenet");

        filmRepository.deleteFilmByTitle("Tenet");

        int after = filmRepository.findFilmsByTitleContaining("Tenet").size();

        Assertions.assertThat(before).isEqualTo(after);
    }

    @Test
    void findAllFilmsBetweenYear() {

        Film film1 = new Film();
        film1.setTitle("Film1");
        film1.setYear(2000);

        Film film2 = new Film();
        film2.setTitle("Film2");
        film2.setYear(2005);

        Film film3 = new Film();
        film3.setTitle("Film3");
        film3.setYear(2010);

        filmRepository.save(film1);
        filmRepository.save(film2);
        filmRepository.save(film3);


        int size = filmRepository.findFilmByYearBetween(2002, 2012).size();

        Assertions.assertThat(2).isEqualTo(size);


    }


}
