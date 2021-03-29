package com.example.movies;

import com.example.movies.dataFactory.Creator;
import com.example.movies.entity.Film;
import com.example.movies.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import([Creator.class])
class FilmRepositoryGroovyTest {


    @Autowired
    private Creator creator;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    void newFilmTest() {

        int before = filmRepository.findAll().size();

        Film film = new Film(title: "TestGroovy1")
        creator.save(film);

        int after = filmRepository.findAll().size();

        Assertions.assertThat(after).isEqualTo(before+1);

        def readFromDb = filmRepository.findById(film.getId()).get()
        Assertions.assertThat(readFromDb.getTitle()).isEqualTo("TestGroovy1");
        Assertions.assertThat(readFromDb.getLength()).isEqualTo(0);


    }

    @Test
    void findFilm() {

        Film film = new Film(title: "TestGroovy2")
        creator.save(film);

        Assertions.assertThat(filmRepository.findFilmByTitleContains("TestGroovy2").getTitle()).isEqualTo("TestGroovy2");

    }

    @Test
    void deleteFilm() {

        int before = filmRepository.findFilmsByTitleContaining("TestGroovy3").size();

        Film film = new Film(title: "TestGroovy3")
        creator.save(film);

        Assertions.assertThat(filmRepository.findFilmByTitleContains("TestGroovy3").getTitle()).isEqualTo("TestGroovy3");

        filmRepository.deleteFilmByTitle("TestGroovy3");

        int after = filmRepository.findFilmsByTitleContaining("TestGroovy3").size();

        Assertions.assertThat(before).isEqualTo(after);
    }

    @Test
    void findAllFilmsBetweenYear() {


        Film film1 = new Film(title: "TestGroovyY1", year: 3000)
        creator.save(film1);
        Film film2 = new Film(title: "TestGroovyY1", year: 3005)
        creator.save(film2);
        Film film3 = new Film(title: "TestGroovyY1", year: 3010)
        creator.save(film3);

        int size = filmRepository.findFilmByYearBetween(3002, 3012).size();

        Assertions.assertThat(2).isEqualTo(size);


    }


}
